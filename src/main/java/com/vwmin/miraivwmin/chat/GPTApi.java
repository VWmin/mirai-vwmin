package com.vwmin.miraivwmin.chat;

import com.vwmin.restproxy.annotations.Header;
import com.vwmin.restproxy.annotations.Json;
import com.vwmin.restproxy.annotations.POST;

/**
 * @author vwmin
 * @date 2023/3/2 14:49
 */

public interface GPTApi {
    @POST("/chat/completions")
    @Header(k="Authorization", v="Bearer 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")
    ResponseBody chat(@Json RequestBody body);
}
