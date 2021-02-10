package com.vwmin.miraivwmin.event;

import net.mamoe.mirai.contact.Contact;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/11 15:39
 */
public interface CommandInterceptor {
    String process(String miraiCode);
    boolean isMatch(String miraiCode, Contact subject);
}
