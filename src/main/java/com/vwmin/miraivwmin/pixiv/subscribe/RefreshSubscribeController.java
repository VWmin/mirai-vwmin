package com.vwmin.miraivwmin.pixiv.subscribe;

import com.vwmin.miraivwmin.util.EmptyCommand;
import com.vwmin.miraivwmin.core.Reply;
import com.vwmin.miraivwmin.core.message.MessageBuilder;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Message;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/31 19:15
 */
//@CommandController(bind = "刷新订阅")
public class RefreshSubscribeController implements Reply<EmptyCommand> {

    private final AutoDetectNewWorks autoDetectNewWorks;

    public RefreshSubscribeController(AutoDetectNewWorks autoDetectNewWorks) {
        this.autoDetectNewWorks = autoDetectNewWorks;
    }

    @Override
    public Message reply(EmptyCommand command, Contact subject, User sender) {
        autoDetectNewWorks.detectSingle(String.valueOf(sender.getId()));
        return new MessageBuilder().plaintext("少女祈祷中...").build();
    }
}
