package com.vwmin.miraivwmin.setu;

import com.vwmin.miraivwmin.bot.CommandController;
import com.vwmin.miraivwmin.bot.Reply;
import com.vwmin.miraivwmin.event.MessageBuilder;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.*;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/7 17:12
 */
@CommandController(bind = "setu", alias = {"一张色图"})
public class SetuCommandController implements Reply<SetuCommand> {

    private final SetuApi api;

    public SetuCommandController(SetuApi api){
        this.api = api;
    }

    @Override
    public Message reply(SetuCommand command, Contact subject, User sender) {
        SetuEntity setuEntity = command.call(api);
        MessageBuilder builder = new MessageBuilder();
        if (setuEntity == null) {
            return builder.plaintext("Exception: empty response.").build();
        }

        SetuEntity.DataBean dataBean = setuEntity.getData().get(0);
        String imgUrl = dataBean.getUrl();
        String file = dataBean.getPid()+imgUrl.substring(imgUrl.length()-4);

        builder.at(sender.getId()).image(subject, file, imgUrl);

        if (setuEntity.getQuota() <= 5){
            builder.plaintext("Alert! 掏空倒计时：" + setuEntity.getQuota());
        }

        return builder.build();
    }
}
