package com.vwmin.miraivwmin.pixiv;

import com.vwmin.miraivwmin.event.ImageUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author vwmin
 * @version 1.0
 * @date 2020/4/13 13:21
 */
@Slf4j
public class IllustUtils {
    private IllustUtils(){}

    public static String getMetaSinglePage(ListIllustResponse.IllustsBean illust){
        if(illust.getPage_count() > 1){
            return illust.getMeta_pages().get(0).getImage_urls().getOriginal();
        }else{
            return illust.getMeta_single_page().getOriginal_image_url();
        }
    }

    public static String getImgType(ListIllustResponse.IllustsBean illust){
        String str;
        if(illust.getPage_count() > 1){
            str = illust.getMeta_pages().get(0).getImage_urls().getOriginal();
        }else{
            str = illust.getMeta_single_page().getOriginal_image_url();
        }
        return str.substring(str.length()-4);
    }

    public static String genFileName(ListIllustResponse.IllustsBean illust){
        int id = illust.getId();
        return id+getImgType(illust);
    }


    public static void asynchronousDownload(List<ListIllustResponse.IllustsBean> illusts) throws IOException {
        if(illusts == null || illusts.size()==0 ) {
            return;
        }
        int size = illusts.size();

        /*
         * 核心线程池大小
         * 最大线程池大小
         * 线程池最大空闲时间
         * 时间单位
         * 线程等待队列
         * 线程创建工厂
         * 拒绝策略
         */
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                1,
                size,
                1000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(size),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
        );

        log.info("正在启动线程...");

        for (ListIllustResponse.IllustsBean illust : illusts) {
            String filename = genFileName(illust);
            if (!ImageUtils.isExist(filename)) {
                pool.execute(new PixivDownloadTask(illust));
            } else {
                log.info("pixiv id: {} 已存在，将跳过", illust.getId());
            }
        }

        pool.shutdown(); //继续执行先前的任务，但不接收新任务

        try {
            log.info("等待所有线程结束...");
            pool.awaitTermination(3, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            log.error("等待线程结束失败...");
            e.printStackTrace();
        } finally {
            log.info("所有线程已结束，共完成：{}.", size);
        }
    }


    //fixme:其实有反代可以用 不用管Header了
    private static class PixivDownloadTask implements Runnable{
        private final Header HEADER = new BasicHeader("Referer", "https://app-api.pixiv.net/");
        private final long id;
        private final String filename;
        private final String url;

        public PixivDownloadTask(ListIllustResponse.IllustsBean illust){
            this.id = illust.getId();
            this.filename = genFileName(illust);
            this.url = getMetaSinglePage(illust).replace("i.pximg.net", "i.pixiv.cat");
        }

        @Override
        public void run() {
            log.info("正在下载 >> " + id);
            try {
                ImageUtils.downloadImage(filename, url, new Header[]{HEADER});
                log.info("下载已发起 >> " + id);
            } catch (IOException e) {
                log.warn("序号{}下载失败，{}...", id, e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
