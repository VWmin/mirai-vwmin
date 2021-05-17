package com.vwmin.miraivwmin.saucenao;

import com.vwmin.miraivwmin.core.CommandInterceptor;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.message.code.MiraiCode;
import net.mamoe.mirai.message.data.Image;
import org.springframework.stereotype.Component;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/11 20:32
 */
@Component
public class SaucenaoCommandInterceptor  implements CommandInterceptor {

    @Override
    public String process(String miraiCode) {
        Image image = MiraiCode.deserializeMiraiCode(miraiCode).get(Image.Key);
        return image == null ? "" : "search " + Image.queryUrl(image);

    }

    @Override
    public boolean isMatch(String miraiCode, Contact subject) {
        return miraiCode.startsWith("search") || miraiCode.startsWith("搜图");
    }
}
