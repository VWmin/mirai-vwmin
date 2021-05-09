package com.vwmin.miraivwmin.bot;

import com.vwmin.miraivwmin.event.MyEventHandler;
import net.mamoe.mirai.Bot;
import org.springframework.context.ApplicationContext;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/5/9 18:04
 */
public class BotThread implements Runnable{

    private final Bot bot;
    private final ApplicationContext context;

    public BotThread(Bot bot, ApplicationContext context) {
        this.bot = bot;
        this.context = context;
    }

    @Override
    public void run() {

        bot.login();

        bot.getEventChannel().registerListenerHost(new MyEventHandler(context, bot));

        bot.join();
    }
}
