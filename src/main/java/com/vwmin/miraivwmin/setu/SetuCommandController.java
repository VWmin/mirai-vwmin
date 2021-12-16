package com.vwmin.miraivwmin.setu;

import com.vwmin.miraivwmin.core.CommandController;
import com.vwmin.miraivwmin.core.Reply;
import com.vwmin.miraivwmin.core.BotHandler;
import com.vwmin.miraivwmin.core.message.ForwardMessageBuilder;
import com.vwmin.miraivwmin.core.message.MessageBuilder;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.*;

import java.util.List;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/7 17:12
 */
@Slf4j
@CommandController(bind = "setu", alias = {"一张色图"})
public class SetuCommandController implements Reply<SetuCommand> {

    private final SetuApiV2 apiV2;
    private final BotHandler botHandler;

    public SetuCommandController(SetuApiV2 apiV2, BotHandler botHandler){
        this.apiV2 = apiV2;
        this.botHandler = botHandler;
    }

    @Override
    public Message reply(SetuCommand command, Contact subject, User sender) {
        SetuEntity setuEntity = command.call(apiV2);
        Bot bot = botHandler.getBot();



        List<SetuEntity.DataBean> setuList = setuEntity.getData();
        if (setuList.size() == 0) {
            return new MessageBuilder().plaintext("啥也没找到惹～").build();
        }
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

    private Message singleSetuMessage(User sender, Contact subject, SetuEntity.DataBean setu){
        MessageBuilder builder = new MessageBuilder();
        String imgUrl = setu.getUrls().getOriginal();
        String file = setu.getPid() + imgUrl.substring(imgUrl.length() - 4);
        return builder
                .at(sender.getId())
                .image(subject, file, imgUrl)
                .build();
    }
}
