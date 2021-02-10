package com.vwmin.miraivwmin.pixiv.subscribe;

import com.vwmin.miraivwmin.bot.CommandController;
import com.vwmin.miraivwmin.bot.Reply;
import com.vwmin.miraivwmin.event.MessageBuilder;
import com.vwmin.miraivwmin.pixiv.PixivApi;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Message;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/31 16:46
 */
@CommandController(bind = "订阅")
public class SubscribeController implements Reply<SubscribeCommand> {

    private final PixivApi pixivApi;
    private final AutoDetectNewWorks autoDetectNewWorks;

    public SubscribeController(PixivApi pixivApi, AutoDetectNewWorks autoDetectNewWorks) {
        this.pixivApi = pixivApi;
        this.autoDetectNewWorks = autoDetectNewWorks;
    }


    @Override
    public Message reply(SubscribeCommand command, Contact subject, User sender) {
        command.call(pixivApi);
        String response = autoDetectNewWorks.subscribe(sender.getId(), command.username);
        return new MessageBuilder().plaintext(response).build();
    }
}
