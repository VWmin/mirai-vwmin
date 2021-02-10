package com.vwmin.miraivwmin.bot;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/7 16:41
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface CommandController {

    String bind();
    String[] alias() default {};
}
