package com.vwmin.miraivwmin.setu;

import com.vwmin.restproxy.annotations.*;

/**
 * @author vwmin
 * @date 2021/12/15 12:36 下午
 */

public interface SetuApiV2 {
    @LogRequest
    @POST("/setu/v2")
    SetuEntity setu(
            @Json SetuRequestEntity requestEntity
    );
}
