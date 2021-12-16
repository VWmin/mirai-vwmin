package com.vwmin.miraivwmin.setu;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author vwmin
 * @date 2021/12/15 12:39 下午
 */

@Data
public class SetuRequestEntity {
    private int num = 1;
    private int r18 = 0;
    /**
     * 一维数组的时候是元素间是and
     * 二维数组的时候元素间是or，数组间是and
     */
    List<String> tag = new ArrayList<>();
    String proxy = "i.pixiv.re";

    public static class Builder {
        SetuRequestEntity request = new SetuRequestEntity();

        public Builder num(int num) {
            request.setNum(num);
            return this;
        }

        public Builder r18(int r18) {
            request.setR18(r18);
            return this;
        }

        public Builder tag(String tag) {
            request.tag.add(tag);
            return this;
        }

        public Builder tag(List<String> tags) {
            if(tags != null && tags.size()!=0) {
                request.tag.addAll(tags);
            }
            return this;
        }

        public SetuRequestEntity build(){
            return request;
        }
    }

}
