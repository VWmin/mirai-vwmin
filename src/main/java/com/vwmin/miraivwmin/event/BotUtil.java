package com.vwmin.miraivwmin.event;

import com.vwmin.miraivwmin.bot.BotThread;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/2/4 13:52
 */
@Component
public class BotUtil {
    private Bot bot = null;
    private final ApplicationContext context;
    public BotUtil(ApplicationContext context){
        this.context = context;
    }

    @Async
    @PostConstruct
    public void loadBot(){
        Bot bot = BotFactory.INSTANCE.newBot(212746897, "justmiss.qq", new BotConfiguration() {
            {
                //保存设备信息到文件
                fileBasedDeviceInfo("deviceInfo.json");
                //修改登录协议
                setProtocol(MiraiProtocol.ANDROID_PHONE);
                // setLoginSolver();
                // setBotLoggerSupplier();

                // noNetworkLog();

            }
        });

        this.bot = bot;

        new Thread(new BotThread(bot, context), "bot").start();
    }

    public Bot getBot() {
        return bot;
    }
}
