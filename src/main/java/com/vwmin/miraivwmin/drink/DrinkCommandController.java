package com.vwmin.miraivwmin.drink;

import com.vwmin.miraivwmin.core.BotHandler;
import com.vwmin.miraivwmin.core.CommandController;
import com.vwmin.miraivwmin.core.Reply;
import com.vwmin.miraivwmin.core.message.MessageBuilder;
import com.vwmin.miraivwmin.util.EmptyCommand;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Message;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


/**
 * @author vwmin
 * @version 1.0
 * @date 2021/5/17 21:49
 */
@EnableScheduling
@CommandController(bind = "三点几了")
public class DrinkCommandController implements Reply<EmptyCommand> {

    private final BotHandler botHandler;

    private final String content = "喂! 三點幾了喂！做做做做撚啊做。飲茶先啊，三點幾，飲茶先啦。" +
            "做咁多都冇用，老細唔錫你啦喂。喂，飲啖茶先啦喂。做碌鳩啊做。";

    private final String path2File = "voice/drink.amr";

    public DrinkCommandController(BotHandler botHandler) {
        this.botHandler = botHandler;
    }

    @Override
    public Message reply(EmptyCommand command, Contact subject, User sender) {
        return new MessageBuilder().voice(subject, path2File);
    }

//    @Scheduled(cron = "0 22 22 1/1 * ?")
    @Scheduled(cron = "0 0 15 1/1 * ?")
    public void hint(){
        Group group = botHandler.getBot().getGroupOrFail(570500496L);
        group.sendMessage(content);
        group.sendMessage(new MessageBuilder().voice(group, path2File));
    }
}
