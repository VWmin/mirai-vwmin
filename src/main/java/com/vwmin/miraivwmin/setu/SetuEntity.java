package com.vwmin.miraivwmin.setu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/7 15:06
 */
@NoArgsConstructor
@Data
public class SetuEntity {

    @JsonProperty("error")
    private String error;
    @JsonProperty("data")
    private List<DataBean> data;

    @NoArgsConstructor
    @Data
    public static class DataBean {
        @JsonProperty("pid")
        private Integer pid;
        @JsonProperty("p")
        private Integer p;
        @JsonProperty("uid")
        private Integer uid;
        @JsonProperty("title")
        private String title;
        @JsonProperty("author")
        private String author;
        @JsonProperty("r18")
        private Boolean r18;
        @JsonProperty("width")
        private Integer width;
        @JsonProperty("height")
        private Integer height;
        @JsonProperty("tags")
        private List<String> tags;
        @JsonProperty("ext")
        private String ext;
        @JsonProperty("uploadDate")
        private Long uploadDate;
        @JsonProperty("urls")
        private UrlsDTO urls;

        @NoArgsConstructor
        @Data
        public static class UrlsDTO {
            @JsonProperty("original")
            private String original;
        }
    }
}