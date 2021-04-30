package com.vwmin.miraivwmin;

import com.vwmin.miraivwmin.bot.CommandController;
import com.vwmin.miraivwmin.bot.EmptyCommand;
import com.vwmin.miraivwmin.bot.Reply;
import com.vwmin.miraivwmin.event.BotUtil;
import com.vwmin.miraivwmin.event.ForwardMessageBuilder;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.*;
import net.mamoe.mirai.utils.ExternalResource;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 *
 * MessageChainBuilder混合测试：
 *  1. 添加ForwardMessage后发送到群聊为转发消息
 *  2. 混合转发消息和普通消息，发送时会报错
 *  结论：可通过MessageChainBuilder和ForwardMessageKt.toForwardMessage(Message, Long, String)发送转发消息，
 *  定制性仅限转发内容，且发送给非群组似乎无效
 *
 * ForwardMessageBuilder测试:
 *  1. builder.named().says() 指定名称并指定消息，
 *  2. builder.says(bot, Message) 发送人为bot发送消息
 *  3. named() at() 会创建一个BuilderNode，用于构建发送人信息、发送时间
 *  4. says() 返回builder主体，并构建一条单独消息
 *
 *
 * @author vwmin
 * @version 1.0
 * @date 2021/2/4 11:29
 */
//@CommandController(bind = "转发")
public class ForwardController implements Reply<EmptyCommand> {

    private final BotUtil botUtil;

    public ForwardController(BotUtil botUtil) {
        this.botUtil = botUtil;
    }

    @Override
    public Message reply(EmptyCommand command, Contact subject, User sender) {
        Bot bot = botUtil.getBot();
        Image image = subject.uploadImage(ExternalResource.create(new File("d://share//test.jpg")));

        Group group = bot.getGroup(813116844);
        assert group != null;

        Friend friend = bot.getFriend(1903215898);
        assert friend != null;

//        group.sendMessage(new ForwardMessageBuilder(bot, group).botSays("contact is group, to group").botSays("redundant msg.").botSays(image).build());
        friend.sendMessage(new ForwardMessageBuilder(bot, friend).botSays("contact is friend, to friend").botSays("redundant msg.").botSays(image).build());
//
//        group.sendMessage(new ForwardMessageBuilder(bot, friend).botSays("contact is friend, to group").botSays("redundant msg.").botSays(image).build());
//        friend.sendMessage(new ForwardMessageBuilder(bot, group).botSays("contact is group, to friend").botSays("redundant msg.").botSays(image).build());

//        friend.sendMessage(new ForwardMessageBuilder(bot, sender).botSays("contact is user, to friend").botSays("redundant msg.").botSays(image).build());
//        friend.sendMessage(new ForwardMessageBuilder(bot, subject).botSays("contact is subject, to friend").botSays("redundant msg.").botSays(image).build());


            return null;
//        return new ForwardMessageBuilder(bot, friend).botSays("contact is friend, to subject").botSays("redundant msg.").botSays(image).build();
    }
}
