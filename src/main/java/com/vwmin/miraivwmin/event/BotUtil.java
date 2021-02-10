package com.vwmin.miraivwmin.event;

import net.mamoe.mirai.Bot;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

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

    public Bot getBot() {
        if (bot == null){
            bot = (Bot) context.getBean("bot");
        }
        return bot;
    }
}
