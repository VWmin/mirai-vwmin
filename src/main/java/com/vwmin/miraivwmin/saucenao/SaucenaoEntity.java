package com.vwmin.miraivwmin.saucenao;

import lombok.Data;

import java.util.List;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/11 16:30
 */
@Data
public class SaucenaoEntity {
    /**
     * header : {"userId":0,"account_type":0,"short_limit":"4","long_limit":"100","long_remaining":88,"short_remaining":3,"status":0,"results_requested":3,"index":{"0":{"status":0,"parent_id":0,"id":0,"results":3},"2":{"status":0,"parent_id":2,"id":2,"results":3},"3":{"status":0,"parent_id":3,"id":3,"results":3},"4":{"status":0,"parent_id":4,"id":4,"results":3},"5":{"status":0,"parent_id":5,"id":5,"results":3},"51":{"status":0,"parent_id":5,"id":51,"results":3},"52":{"status":0,"parent_id":5,"id":52,"results":3},"53":{"status":0,"parent_id":5,"id":53,"results":3},"6":{"status":0,"parent_id":6,"id":6,"results":3},"8":{"status":0,"parent_id":8,"id":8,"results":3},"9":{"status":0,"parent_id":9,"id":9,"results":24},"10":{"status":0,"parent_id":10,"id":10,"results":3},"11":{"status":0,"parent_id":11,"id":11,"results":3},"12":{"status":1,"parent_id":9,"id":12},"16":{"status":0,"parent_id":16,"id":16,"results":3},"18":{"status":0,"parent_id":18,"id":18,"results":3},"19":{"status":0,"parent_id":19,"id":19,"results":3},"20":{"status":0,"parent_id":20,"id":20,"results":3},"21":{"status":0,"parent_id":21,"id":21,"results":3},"211":{"status":0,"parent_id":21,"id":211,"results":3},"22":{"status":0,"parent_id":22,"id":22,"results":3},"23":{"status":0,"parent_id":23,"id":23,"results":3},"24":{"status":0,"parent_id":24,"id":24,"results":3},"25":{"status":1,"parent_id":9,"id":25},"26":{"status":1,"parent_id":9,"id":26},"27":{"status":1,"parent_id":9,"id":27},"28":{"status":1,"parent_id":9,"id":28},"29":{"status":1,"parent_id":9,"id":29},"30":{"status":1,"parent_id":9,"id":30},"31":{"status":0,"parent_id":31,"id":31,"results":3},"32":{"status":0,"parent_id":32,"id":32,"results":3},"33":{"status":0,"parent_id":33,"id":33,"results":3},"34":{"status":0,"parent_id":34,"id":34,"results":3},"35":{"status":0,"parent_id":35,"id":35,"results":3},"36":{"status":0,"parent_id":36,"id":36,"results":3},"37":{"status":0,"parent_id":37,"id":37,"results":3}},"search_depth":"128","minimum_similarity":38.8,"query_image_display":"userdata/XbIMVguCD.png.png","query_image":"XbIMVguCD.png","results_returned":3}
     * results : [{"header":{"similarity":"88.07","thumbnail":"http://img1.saucenao.com/res/pixiv/3701/37016225_m.jpg?auth=seKzr4jmKUT2uXS9umreow&exp=1567492475","index_id":5,"index_name":"Index #5: Pixiv Images - 37016225_m.jpg"},"data":{"ext_urls":["https://www.pixiv.net/member_illust.php?mode=medium&illust_id=37016225"],"title":"夏風前線","pixiv_id":37016225,"member_name":"長乃","member_id":1975032}},{"header":{"similarity":"94.14","thumbnail":"http://img3.saucenao.com/booru/f/4/f4f5bff92c5fbaf9c238856d9bcccc90_4.jpg","index_id":9,"index_name":"Index #9: Danbooru - f4f5bff92c5fbaf9c238856d9bcccc90_0.jpg"},"data":{"ext_urls":["https://danbooru.donmai.us/post/show/1462901","https://gelbooru.com/index.php?page=post&s=view&id=1937624","https://chan.sankakucomplex.com/post/show/3227935"],"danbooru_id":1462901,"gelbooru_id":1937624,"sankaku_id":3227935,"creator":"naga-no","material":"original","characters":"","source":"http://i2.pixiv.net/img54/img/naga-no/37016225.png"}},{"header":{"similarity":"37.80","thumbnail":"http://img3.saucenao.com/booru/c/5/c5078f54115fd16a7034d1a589008a1b_4.jpg","index_id":9,"index_name":"Index #9: Danbooru - c5078f54115fd16a7034d1a589008a1b_0.jpg"},"data":{"ext_urls":["https://danbooru.donmai.us/post/show/2631454","https://gelbooru.com/index.php?page=post&s=view&id=3561310","https://chan.sankakucomplex.com/post/show/5874309"],"danbooru_id":2631454,"gelbooru_id":3561310,"sankaku_id":5874309,"creator":"ti owo","material":"touhou","characters":"kawashiro nitori","source":"http://i2.pixiv.net/img-original/img/2017/02/13/00/09/04/61423417"}}]
     */

    private HeaderBean header;
    private List<ResultsBean> results;


    @Data
    public static class HeaderBean {
        /**
         * userId : 0
         * account_type : 0
         * short_limit : 4
         * long_limit : 100
         * long_remaining : 88
         * short_remaining : 3
         * status : 0
         * results_requested : 3
         * index : {"0":{"status":0,"parent_id":0,"id":0,"results":3},"2":{"status":0,"parent_id":2,"id":2,"results":3},"3":{"status":0,"parent_id":3,"id":3,"results":3},"4":{"status":0,"parent_id":4,"id":4,"results":3},"5":{"status":0,"parent_id":5,"id":5,"results":3},"51":{"status":0,"parent_id":5,"id":51,"results":3},"52":{"status":0,"parent_id":5,"id":52,"results":3},"53":{"status":0,"parent_id":5,"id":53,"results":3},"6":{"status":0,"parent_id":6,"id":6,"results":3},"8":{"status":0,"parent_id":8,"id":8,"results":3},"9":{"status":0,"parent_id":9,"id":9,"results":24},"10":{"status":0,"parent_id":10,"id":10,"results":3},"11":{"status":0,"parent_id":11,"id":11,"results":3},"12":{"status":1,"parent_id":9,"id":12},"16":{"status":0,"parent_id":16,"id":16,"results":3},"18":{"status":0,"parent_id":18,"id":18,"results":3},"19":{"status":0,"parent_id":19,"id":19,"results":3},"20":{"status":0,"parent_id":20,"id":20,"results":3},"21":{"status":0,"parent_id":21,"id":21,"results":3},"211":{"status":0,"parent_id":21,"id":211,"results":3},"22":{"status":0,"parent_id":22,"id":22,"results":3},"23":{"status":0,"parent_id":23,"id":23,"results":3},"24":{"status":0,"parent_id":24,"id":24,"results":3},"25":{"status":1,"parent_id":9,"id":25},"26":{"status":1,"parent_id":9,"id":26},"27":{"status":1,"parent_id":9,"id":27},"28":{"status":1,"parent_id":9,"id":28},"29":{"status":1,"parent_id":9,"id":29},"30":{"status":1,"parent_id":9,"id":30},"31":{"status":0,"parent_id":31,"id":31,"results":3},"32":{"status":0,"parent_id":32,"id":32,"results":3},"33":{"status":0,"parent_id":33,"id":33,"results":3},"34":{"status":0,"parent_id":34,"id":34,"results":3},"35":{"status":0,"parent_id":35,"id":35,"results":3},"36":{"status":0,"parent_id":36,"id":36,"results":3},"37":{"status":0,"parent_id":37,"id":37,"results":3}}
         * search_depth : 128
         * minimum_similarity : 38.8
         * query_image_display : userdata/XbIMVguCD.png.png
         * query_image : XbIMVguCD.png
         * results_returned : 3
         */

        private int user_id;
        private int account_type;
        private String short_limit;
        private String long_limit;
        private int long_remaining;
        private int short_remaining;
        private int status;
        private int results_requested;
        private Object index;
        private String search_depth;
        private double minimum_similarity;
        private String query_image_display;
        private String query_image;
        private int results_returned;

    }

    @Data
    public static class ResultsBean {
        /**
         * header : {"similarity":"88.07","thumbnail":"http://img1.saucenao.com/res/pixiv/3701/37016225_m.jpg?auth=seKzr4jmKUT2uXS9umreow&exp=1567492475","index_id":5,"index_name":"Index #5: Pixiv Images - 37016225_m.jpg"}
         * data : {"ext_urls":["https://www.pixiv.net/member_illust.php?mode=medium&illust_id=37016225"],"title":"夏風前線","pixiv_id":37016225,"member_name":"長乃","member_id":1975032}
         */

        private HeaderBeanX header;
        private DataBean data;


        @Data
        public static class HeaderBeanX {
            /**
             * similarity : 88.07
             * thumbnail : http://img1.saucenao.com/res/pixiv/3701/37016225_m.jpg?auth=seKzr4jmKUT2uXS9umreow&exp=1567492475
             * index_id : 5
             * index_name : Index #5: Pixiv Images - 37016225_m.jpg
             */

            private String similarity;
            private String thumbnail;
            private int index_id;
            private String index_name;


        }

        @Data
        public static class DataBean {
            /**
             * ext_urls : ["https://www.pixiv.net/member_illust.php?mode=medium&illust_id=37016225"]
             * title : 夏風前線
             * pixiv_id : 37016225
             * member_name : 長乃
             * member_id : 1975032
             */

            private String title;
            private int pixiv_id;
            private String member_name;
            private int member_id;
            private List<String> ext_urls;

            //            private List<String> creator;
            private String eng_name;
            private String jp_name;


        }
    }
}
