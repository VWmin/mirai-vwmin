package com.vwmin.miraivwmin.setu;

import com.vwmin.restproxy.annotations.GET;
import com.vwmin.restproxy.annotations.LogRequest;
import com.vwmin.restproxy.annotations.Query;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/7 15:06
 */
public interface SetuApi {
    @LogRequest
    @GET("/setu/?apikey=868808285e98546c502464&proxy=i.pixiv.re")
    SetuEntity setu(
            @Query(value = "r18", required = false) int r18,
            @Query(value = "keyword", required = false) String keyword,
            @Query(value = "num", required = false) int num,
            @Query(value = "proxy", required = false) String proxy,
            @Query(value = "size1200", required = false) String size100
    );
}
