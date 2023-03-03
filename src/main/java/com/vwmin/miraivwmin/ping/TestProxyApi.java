package com.vwmin.miraivwmin.ping;

import com.vwmin.restproxy.annotations.GET;

public interface TestProxyApi {
    @GET("/")
    public String get();
}
