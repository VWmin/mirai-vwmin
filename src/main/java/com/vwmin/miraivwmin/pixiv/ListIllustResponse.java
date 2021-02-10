package com.vwmin.miraivwmin.pixiv;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ListIllustResponse implements Serializable {

    private List<IllustsBean> illusts;
    private String next_url ;

    @Data
    public static class IllustsBean implements Serializable{
        private int id;
        private String title;
        private String type;
        private ImageUrlsBean image_urls;
        private String caption;
        private int restrict;
        private UserBean user;
        private String create_date;
        private int page_count;
        private int width;
        private int height;
        private int sanity_level;
        private int x_restrict;
        private Object series;
        private MetaSinglePageBean meta_single_page;
        private int total_view;
        private int total_bookmarks;
        private boolean is_bookmarked;
        private boolean visible;
        private boolean is_muted;
        private List<TagsBean> tags;
        private List<String> tools;
        private List<MetaPagesBean> meta_pages;
        private List<ListIllustResponse> illust;

        @Data
        public static class ImageUrlsBean  implements Serializable {
            private String square_medium;
            private String medium;
            private String large;
            private String original;
        }

        @Data
        public static class MetaSinglePageBean  implements Serializable{
            private String original_image_url;
        }

        @Data
        public static  class TagsBean implements Serializable {
            private String name;
        }

        @Data
        public static class MetaPagesBean implements Serializable  {
            private ImageUrlsBean image_urls;
        }

        @Data
        public static class UserBean implements Serializable  {

            private int id;
            private String name;
            private String account;
            private ProfileImageUrlsBean profile_image_urls;
            private String comment;
            private boolean is_followed;


            @Data
            public static class ProfileImageUrlsBean implements Serializable{
                private String medium;
            }
        }
    }

}