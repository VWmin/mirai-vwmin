package com.vwmin.miraivwmin.pixiv.subscribe;

import com.vwmin.miraivwmin.event.BotUtil;
import com.vwmin.miraivwmin.event.ForwardMessageBuilder;
import com.vwmin.miraivwmin.event.MessageBuilder;
import com.vwmin.miraivwmin.pixiv.IllustUtils;
import com.vwmin.miraivwmin.pixiv.ListIllustResponse;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Friend;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static com.vwmin.miraivwmin.pixiv.IllustUtils.genFileName;
import static com.vwmin.miraivwmin.pixiv.IllustUtils.getMetaSinglePage;


/**
 * @author vwmin
 * @version 1.0
 * @date 2020/4/16 23:21
 */
@Slf4j
@Component
public class NewWorksListener implements ApplicationListener<NewWorksEvent> {

    private final BotUtil botUtil;

    public NewWorksListener(BotUtil botUtil) {
        this.botUtil = botUtil;
    }

    @Async
    @Override
    public void onApplicationEvent(NewWorksEvent event) {
        Bot bot = botUtil.getBot();
        Long who = event.getUserId();
        List<ListIllustResponse.IllustsBean> illusts = event.getIllusts();

        Friend friend = bot.getFriend(who);

        if (friend == null){
            log.warn("未找到好友 {} ", who);
            return;
        }

        log.info("收到推送事件，用户: {}({})，待推送数量: {}", friend.getNick(), friend.getId(), illusts.size());

        if (illusts.size() != 0) {
            try {
                IllustUtils.asynchronousDownload(illusts);
                ForwardMessageBuilder msgBuilder = new ForwardMessageBuilder(bot, friend);
                illusts.forEach((illust) -> msgBuilder.says()
                        .plaintext("来自【"+ illust.getUser().getName() +"】的【"+ illust.getTitle() +"】")
                        .image(friend, genFileName(illust), getMetaSinglePage(illust))
                        .plaintext("\n"));

                friend.sendMessage(msgBuilder.build());

            } catch (IOException e) {
                log.warn("下载新作失败: " + e.getMessage());
                e.printStackTrace();
                friend.sendMessage("Error...");
            }
        }// else {
////            friend.sendMessage(new MessageBuilder().plaintext("暂时没有查找到更新").build());
//        }
    }

}
