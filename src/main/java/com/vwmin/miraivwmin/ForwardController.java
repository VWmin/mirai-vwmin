package com.vwmin.miraivwmin;

import com.vwmin.miraivwmin.bot.CommandController;
import com.vwmin.miraivwmin.bot.EmptyCommand;
import com.vwmin.miraivwmin.bot.Reply;
import com.vwmin.miraivwmin.event.BotUtil;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.*;
import org.springframework.beans.factory.annotation.Autowired;

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
        Group group = bot.getGroupOrFail(813116844);
        Friend friend = bot.getFriendOrFail(1903215898);

        ForwardMessageBuilder builder = new ForwardMessageBuilder(group);
        builder.says(bot, "hello")
                .says(bot, "word");
        group.sendMessage(builder.build());

        ForwardMessageBuilder builder2 = new ForwardMessageBuilder(group);
        builder2.says(bot, "hello")
                .says(bot, "word");
        friend.sendMessage(builder2.build());

        return null;
    }
}
