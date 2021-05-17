package com.vwmin.miraivwmin.roll;

import com.vwmin.miraivwmin.core.CommandController;
import com.vwmin.miraivwmin.core.Reply;
import com.vwmin.miraivwmin.core.message.MessageBuilder;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Message;

import java.util.Random;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/5/17 21:31
 */
@CommandController(bind = "roll")
public class RollCommandController implements Reply<RollCommand> {

    private final Random random = new Random();

    @Override
    public Message reply(RollCommand command, Contact subject, User sender) {
        String result = "    " + (random.nextInt(command.d)+1);
        return (subject instanceof Group)
                ? new MessageBuilder().at(sender.getId()).plaintext(result).build()
                : new MessageBuilder().plaintext(result).build();
    }
}
