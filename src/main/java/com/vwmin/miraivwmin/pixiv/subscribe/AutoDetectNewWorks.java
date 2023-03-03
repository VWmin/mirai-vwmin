package com.vwmin.miraivwmin.pixiv.subscribe;


import com.vwmin.miraivwmin.util.RedisUtil;
import com.vwmin.miraivwmin.pixiv.ListIllustResponse;
import com.vwmin.miraivwmin.pixiv.PixivApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author vwmin
 * @version 1.0
 * @date 2020/4/16 23:13
 */
@Slf4j
//@Service
//@EnableAsync
//@EnableScheduling
public class AutoDetectNewWorks {

    static final String NEW_WORKS = "newWorks"; // QQ id -> Set<IllustId>
    static final String SUBSCRIBERS = "subscribers"; // QQ id (subscriber) -> pixiv username

    private final
    PixivApi api;

    private final
    ApplicationEventPublisher publisher;

    public AutoDetectNewWorks(PixivApi api, ApplicationEventPublisher publisher) {
        this.api = api;
        this.publisher = publisher;
    }


    @Scheduled(cron = "0 30 * * * ?")
    public void detect(){
        log.info("定时任务启动...");
        Map<String, Object> subscribers = RedisUtil.getCache(SUBSCRIBERS);
        subscribers.forEach((subscriber, pixivUsername) -> detectSingle(subscriber, (String) pixivUsername));
        log.info("定时任务结束...");
    }

    public void detectSingle(String subscriber){
        String pixivUsername = (String) RedisUtil.get(SUBSCRIBERS, subscriber);
        detectSingle(subscriber, pixivUsername);
    }

    @Async
    public void detectSingle(String subscriber, String pixivUsername){
        log.info("开始检查pixiv订阅更新...");

        List<ListIllustResponse.IllustsBean> need2Post = new ArrayList<>();
//        need2Post.addAll(api.getNewWorks(pixivUsername).getIllusts().subList(0, 3));
        api.getNewWorks(pixivUsername).getIllusts().forEach((illust) ->{
            if (!RedisUtil.isMember(NEW_WORKS, subscriber, illust.getId())){
                need2Post.add(illust);
                RedisUtil.add2Set(NEW_WORKS, subscriber, illust.getId());
            }
        });

        log.info("got {} new works for {}", need2Post.size(), subscriber);

        publisher.publishEvent(new NewWorksEvent(this, Long.valueOf(subscriber), need2Post));


        log.info("检查结束...");
    }

    public String subscribe(Long subscriber, String pixivUsername){
        //todo:如果已经订阅则...
        RedisUtil.put(SUBSCRIBERS, String.valueOf(subscriber), pixivUsername);
        //初始化更新状态
        api.getNewWorks(pixivUsername).getIllusts().forEach(illust ->
            RedisUtil.add2Set(NEW_WORKS, String.valueOf(subscriber), illust.getId())
        );
        return "订阅成功";
    }

}
