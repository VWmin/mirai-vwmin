package com.vwmin.miraivwmin.pixiv;

import com.vwmin.restproxy.annotations.Body;
import com.vwmin.restproxy.annotations.GET;
import com.vwmin.restproxy.annotations.POST;
import com.vwmin.restproxy.annotations.Query;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/31 11:24
 */
public interface PixivApi {
    @GET("/illust/new?restrict=all")
    ListIllustResponse getNewWorks(@Query("username") String username);

    @POST("/login")
    String login(@Body("username") String username, @Body("password") String password);
}
