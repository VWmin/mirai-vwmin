package com.vwmin.miraivwmin.core;

import com.vwmin.miraivwmin.core.message.MessageBuilder;
import com.vwmin.miraivwmin.util.ExceptionUtil;
import com.vwmin.miraivwmin.util.TypeUtils;
import kotlin.coroutines.CoroutineContext;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.*;
import org.springframework.context.ApplicationContext;
import picocli.CommandLine;

import javax.validation.constraints.NotNull;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/7 14:48
 */
@Slf4j
public class CommandEvent extends SimpleListenerHost {

    private final
    Map<String, Reply<?>> commandCache;

    private final Map<String, String> alias2bind;

    private final Bot bot;


    private final
    List<CommandInterceptor> commandInterceptors;

    public CommandEvent(ApplicationContext applicationContext, Bot bot) {
        this.commandCache        = new ConcurrentHashMap<>(4);
        this.alias2bind          = new ConcurrentHashMap<>(10);
        this.commandInterceptors = new ArrayList<>();
        this.bot = bot;

        initCommandHandlerBinds(applicationContext);
    }

    private void initCommandHandlerBinds(ApplicationContext applicationContext) {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(CommandController.class);
        beansWithAnnotation.forEach((name, bean) -> {
            if(bean instanceof Reply) {
                CommandController annotation = bean.getClass().getAnnotation(CommandController.class);
                String bind = annotation.bind();
                String[] alias = annotation.alias();

                if (commandCache.containsKey(bind)){
                    String which = commandCache.get(bind).getClass().getSimpleName();
                    throw ExceptionUtil.classError(bean.getClass(), "命令已有其他controller绑定(%s)，请考虑修改bind值(%s)", which, bind);
                }
                commandCache.put(bind, (Reply<?>) bean);

                for (String alia : alias){
                    if (alias2bind.containsKey(alia)){
                        log.warn(ExceptionUtil.classWarning(bean.getClass(), "别名(%s)已被占用，将忽略"), alia);
                    }else {
                        alias2bind.put(alia, bind);
                    }
                }
                alias2bind.put(bind, bind);

            }
        });

        commandInterceptors.addAll(applicationContext.getBeansOfType(CommandInterceptor.class).values());
    }

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception){
        Objects.requireNonNull(bot.getFriend(1903215898L)).sendMessage(
                new MessageBuilder()
                .plaintext(exception.getMessage())
                .build()
        );
        // 处理事件处理时抛出的异常
    }

    @EventHandler
    public void onMessage(@NotNull MessageEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        String miraiCode = event.getMessage().serializeToMiraiCode();
        Contact subject = event.getSubject();
        User sender = event.getSender();

        for (CommandInterceptor interceptor : commandInterceptors){
            if (interceptor.isMatch(miraiCode, subject)) {
                miraiCode = interceptor.process(miraiCode);
            }
        }

        //分割命令
        String[] args = miraiCode.split("\\s+");

        Reply<?> commandController = commandCache.get(alias2bind.getOrDefault(args[0], ""));
        if (commandController == null){
            return ;
        }

        log.info("args >>> " + Arrays.toString(args));

        //获得reply函数
        Method executeMethod = Reply.class.getMethod("reply", Object.class, Contact.class, User.class);
        //获得reply函数泛型的实际类型，用于交给CommandLine注入字段
        Type actualTypeArgument = ((ParameterizedType) commandController.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
        Object commandInstance = TypeUtils.getClass(actualTypeArgument).newInstance();
        // 获得command实例后，写入参数
        new CommandLine(commandInstance).parseArgs(subArgs(args));
        //执行reply函数
        Message message;
        try{
            message = (Message) executeMethod.invoke(commandController, commandInstance, subject, sender);
        }catch (Exception e){
            message = new MessageBuilder()
                    .plaintext("内部错误：" + e.getCause().getMessage())
                    .build();
            log.error("内部错误：{}", e.getCause().getMessage());
            e.printStackTrace();
        }

        //发送消息
        if (message != null) {
            subject.sendMessage(message);
        }

    }

    private String[] subArgs(String[] args) {
        if (args.length == 1){
            return new String[0];
        }
        return Arrays.copyOfRange(args, 1, args.length);
    }

//    @NotNull
//    @EventHandler
//    public ListeningStatus onMessage(@NotNull MessageEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
//        event.subject.sendMessage("received");
//        return ListeningStatus.LISTENING; // 表示继续监听事件
//        // return ListeningStatus.STOPPED; // 表示停止监听事件
//    }
}