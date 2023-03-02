package com.vwmin.miraivwmin.core;

import com.vwmin.miraivwmin.util.DecorateToSlf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/2/4 13:52
 */
@Component
public class BotHandler implements ApplicationRunner {
    private Bot bot = null;
    private final ApplicationContext context;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();


    @Value("${bot.netLog}")
    private String netLogPath;

    @Value("${bot.qq}")
    private long qq;

    @Value("${bot.password}")
    private String password;

    public BotHandler(ApplicationContext context){
        this.context = context;
    }

    public void startBot(){
        if (bot != null) {
            return;
        }

        Bot bot = BotFactory.INSTANCE.newBot(qq, password, new BotConfiguration() {
            {
                //保存设备信息到文件
                fileBasedDeviceInfo("deviceInfo.json");
                //修改登录协议
                setProtocol(MiraiProtocol.ANDROID_WATCH);
                // setLoginSolver();
                setBotLoggerSupplier(bot -> new DecorateToSlf4j());
                // 重定向网络日志到文件
                redirectNetworkLogToDirectory(new File(netLogPath));

            }
        });

        this.bot = bot;
        executorService.submit(()->{
            bot.login();
            bot.getEventChannel().registerListenerHost(new CommandEvent(context, bot));
            bot.join();
        });
    }

    public Bot getBot() {
        return bot;
    }

    @Override
    public void run(ApplicationArguments args) {
        startBot();
    }

    @PreDestroy
    private void destroy(){
        executorService.shutdownNow();
    }
}
