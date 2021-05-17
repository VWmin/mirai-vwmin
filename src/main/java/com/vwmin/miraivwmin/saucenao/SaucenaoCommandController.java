package com.vwmin.miraivwmin.saucenao;

import com.vwmin.miraivwmin.core.CommandController;
import com.vwmin.miraivwmin.core.Reply;
import com.vwmin.miraivwmin.core.message.MessageBuilder;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Message;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/11 16:16
 */
@Slf4j
@CommandController(bind = "search")
public class SaucenaoCommandController implements Reply<SaucenaoCommand> {

    private final SaucenaoApi saucenaoApi;

    public SaucenaoCommandController(SaucenaoApi saucenaoApi) {
        this.saucenaoApi = saucenaoApi;
    }

    @Override
    public Message reply(SaucenaoCommand command, Contact subject, User sender) {
        return mostly(command.call(saucenaoApi), subject, sender);
    }

    public Message mostly(SaucenaoEntity entity, Contact subject, User sender){
        MessageBuilder builder = new MessageBuilder();
        builder.at(sender.getId());

        try{
            SaucenaoEntity.ResultsBean one = entity.getResults().get(0);
            SaucenaoEntity.HeaderBean header = entity.getHeader();

//        log.info("SauceNAO the first result  >>  " + one);

            long timeStamp = System.currentTimeMillis();
            String imageType = one.getHeader().getIndex_name().substring(one.getHeader().getIndex_name().lastIndexOf("."));
            int short_remaining = header.getShort_remaining();
            int long_remaining = header.getLong_remaining();
            double similarity = Double.parseDouble(one.getHeader().getSimilarity());

            String title = "我是图片名";
            if(one.getData().getTitle()!=null){
                title = one.getData().getTitle();
            }else if(one.getData().getJp_name()!=null){
                title = one.getData().getJp_name();
            }else if(one.getData().getEng_name()!=null){
                title = one.getData().getEng_name();
            }
            String author = "我是作者";
            if(one.getData().getMember_name()!=null) {
                author = one.getData().getMember_name();
            }

            // 通用
            String fileName = timeStamp+imageType;
            String fileUrl = one.getHeader().getThumbnail();
//        String fileName;
//        String fileUrl;

//        if(one.getHeader().getIndex_id() == 5){ //Pixiv
//            try{
//                Illust illustById = pixivApi.getIllustById(one.getData().getPixiv_id()).getIllust();
//                fileName = IllustUtils.genFileName(illustById);
//                fileUrl = IllustUtils.getMetaSinglePage(illustById);
//                new PixivDownloadTask(illustById).run();
//            }catch (HttpClientErrorException.NotFound e){
//                fileName = timeStamp+imageType;
//                fileUrl = one.getHeader().getThumbnail();
//            }
//        }else{
//            fileName = timeStamp+imageType;
//            fileUrl = one.getHeader().getThumbnail();
//        }



            if(similarity < 50){
                builder.plaintext("[找到图片相似度过低(" + one.getHeader().getSimilarity() + "%)，结果不给康了嗷，感兴趣请戳↓]\n")
                        .plaintext(one.getData().getExt_urls().get(0));
            }else{
                builder.plaintext("[找到图片相似度：" + one.getHeader().getSimilarity() + "%]\n")
                        .plaintext("[" + title + "]  by  [" + author + "]")
                        .image(subject, fileName, fileUrl);
                if(one.getData().getExt_urls()!=null && one.getData().getExt_urls().size()>0){
                    builder.plaintext(one.getData().getExt_urls().get(0));
                }
            }

            if(long_remaining < 20){
                builder.plaintext("\nwarn >> 24h内剩余次数："+long_remaining);
            }
        }catch (NullPointerException e){
            return builder.plaintext("Exception: empty response.").build();
        }



        return builder.build();
    }
}
