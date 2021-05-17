package com.vwmin.miraivwmin.util;

import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.utils.MiraiLoggerPlatformBase;
import org.jetbrains.annotations.Nullable;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/5/17 19:17
 */
@Slf4j
public class DecorateToSlf4j extends MiraiLoggerPlatformBase {
    @Override
    protected void debug0(@Nullable String s, @Nullable Throwable throwable) {
        log.debug(s, throwable);
    }

    @Override
    protected void error0(@Nullable String s, @Nullable Throwable throwable) {
        log.error(s, throwable);
    }

    @Override
    protected void info0(@Nullable String s, @Nullable Throwable throwable) {
        log.info(s, throwable);
    }

    @Override
    protected void verbose0(@Nullable String s, @Nullable Throwable throwable) {
        log.info(s, throwable);
    }

    @Override
    protected void warning0(@Nullable String s, @Nullable Throwable throwable) {
        log.warn(s, throwable);
    }

    @Nullable
    @Override
    public String getIdentity() {
        return "VWMIN";
    }
}
