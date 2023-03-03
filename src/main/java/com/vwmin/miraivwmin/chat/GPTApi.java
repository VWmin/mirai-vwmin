package com.vwmin.miraivwmin.chat;

import com.vwmin.restproxy.annotations.Header;
import com.vwmin.restproxy.annotations.Headers;
import com.vwmin.restproxy.annotations.Json;
import com.vwmin.restproxy.annotations.POST;

/**
 * @author vwmin
 * @date 2023/3/2 14:49
 */

public interface GPTApi {
    @POST("/chat/completions")
    @Headers(headers = {
            @Header(k = "Authorization", v = "Bearer "),
            @Header(k = "Content-Type", v = "application/json")
    })
    ResponseBody chat(@Json RequestBody body);
}
