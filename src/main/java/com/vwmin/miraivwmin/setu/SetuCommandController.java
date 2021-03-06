package com.vwmin.miraivwmin.setu;

import com.vwmin.miraivwmin.bot.CommandController;
import com.vwmin.miraivwmin.bot.Reply;
import com.vwmin.miraivwmin.event.BotUtil;
import com.vwmin.miraivwmin.event.ForwardMessageBuilder;
import com.vwmin.miraivwmin.event.MessageBuilder;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/7 17:12
 */
@CommandController(bind = "setu", alias = {"一张色图"})
public class SetuCommandController implements Reply<SetuCommand> {

    private final SetuApi api;
    private final BotUtil botUtil;

    public SetuCommandController(SetuApi api, BotUtil botUtil){
        this.api = api;
        this.botUtil = botUtil;
    }

    @Override
    public Message reply(SetuCommand command, Contact subject, User sender) {
        SetuEntity setuEntity = command.call(api);
        Bot bot = botUtil.getBot();

        if (setuEntity.getQuota() == 0){
            return new MessageBuilder()
                    .at(sender.getId())
                    .plaintext("没了")
                    .build();
        }else{
            List<SetuEntity.DataBean> setuList = setuEntity.getData();
            if (setuList.size() == 1){
                return singleSetuMessage(sender, subject, setuList.get(0));
            }else{
                ForwardMessageBuilder builder = new ForwardMessageBuilder(bot, subject);
                for (SetuEntity.DataBean setu : setuList) {
                    builder.botSays(singleSetuMessage(sender, subject, setu));
                }
                return builder.build();
            }

        }
    }

    private Message singleSetuMessage(User sender, Contact subject, SetuEntity.DataBean setu){
        MessageBuilder builder = new MessageBuilder();
        String imgUrl = setu.getUrl();
        String file = setu.getPid() + imgUrl.substring(imgUrl.length() - 4);
        return builder
                .at(sender.getId())
                .image(subject, file, imgUrl)
                .build();
    }
}
