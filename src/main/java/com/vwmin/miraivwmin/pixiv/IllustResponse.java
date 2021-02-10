package com.vwmin.miraivwmin.pixiv;

import lombok.Data;

import java.io.Serializable;

@Data
public class IllustResponse implements Serializable {
    private ListIllustResponse.IllustsBean illust;
}
