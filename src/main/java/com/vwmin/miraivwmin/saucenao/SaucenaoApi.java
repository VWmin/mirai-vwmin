package com.vwmin.miraivwmin.saucenao;

import com.vwmin.restproxy.annotations.GET;
import com.vwmin.restproxy.annotations.Query;

/**
 * @author vwmin
 * @version 1.0
 * @date 2020/4/13 10:21
 */
public interface SaucenaoApi {
    @GET("/search.php?api_key=a78e171b7f868eec16c9bb6eb02117ba8da6a76d")
    SaucenaoEntity search(
            @Query("url") String url,
            @Query("database") Integer database,
            @Query("output_type") Integer outputType,
            @Query("numres") Integer numres
    );
}