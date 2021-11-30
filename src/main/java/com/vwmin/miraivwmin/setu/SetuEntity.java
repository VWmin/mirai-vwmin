package com.vwmin.miraivwmin.setu;

import lombok.Data;

import java.util.List;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/7 15:06
 */
@Data
public class SetuEntity {

    private int code;
    private String msg;
    private int count;
    private List<DataBean> data;

    @Data
    public static class DataBean {
        private int pid;
        private int p;
        private int uid;
        private String title;
        private String author;
        private String url;
        private boolean r18;
        private int width;
        private int height;
        private List<String> tags;
    }


}
