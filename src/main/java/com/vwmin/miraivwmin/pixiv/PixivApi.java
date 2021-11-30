package com.vwmin.miraivwmin.pixiv;

import com.vwmin.restproxy.annotations.*;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/31 11:24
 */
public interface PixivApi {
    @GET("/illust/new?restrict=all")
    ListIllustResponse getNewWorks(@Query("username") String username);

    @POST("/login")
    String login(@Field("username") String username, @Field("password") String password);
}
