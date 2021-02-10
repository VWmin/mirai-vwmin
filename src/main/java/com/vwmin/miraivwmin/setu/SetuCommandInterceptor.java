package com.vwmin.miraivwmin.setu;

import com.vwmin.miraivwmin.event.CommandInterceptor;
import net.mamoe.mirai.contact.Contact;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/11 15:43
 */
@Component
public class SetuCommandInterceptor implements CommandInterceptor {
    private final Pattern REGEX = Pattern.compile(".*([一张色图]).*([一张色图]).*([一张色图]).*([一张色图]).*");

    @Override
    public String process(String miraiCode) {
        return REGEX.matcher(miraiCode).replaceAll("setu");
    }

    @Override
    public boolean isMatch(String miraiCode, Contact subject) {
        return REGEX.matcher(miraiCode).matches();
    }
}
