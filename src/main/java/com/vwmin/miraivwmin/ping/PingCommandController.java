package com.vwmin.miraivwmin.ping;

import com.vwmin.miraivwmin.core.CommandController;
import com.vwmin.miraivwmin.util.EmptyCommand;
import com.vwmin.miraivwmin.core.Reply;
import com.vwmin.miraivwmin.core.message.MessageBuilder;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Message;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/11 17:43
 */
@CommandController(bind = "ping", alias = {"测试1", "测试2"})
public class PingCommandController implements Reply<EmptyCommand> {
    @Override
    public Message reply(EmptyCommand command, Contact subject, User sender) {
        return new MessageBuilder().at(sender.getId()).plaintext("pong!").build();
    }

}
