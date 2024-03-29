package com.vwmin.miraivwmin.core.message;


import com.vwmin.miraivwmin.util.ImageUtils;
import net.mamoe.mirai.contact.AudioSupported;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.message.data.*;
import net.mamoe.mirai.utils.ExternalResource;

import java.io.File;
import java.io.IOException;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/11 16:41
 */
public class MessageBuilder {
    private final MessageChainBuilder builder = new MessageChainBuilder();

    public MessageBuilder() {
    }

    public MessageBuilder plaintext(String text) {
        builder.append(new PlainText(text));
        return this;
    }

    public MessageBuilder at(Long id) {
        builder.append(new At(id));
        return this;
    }

    public MessageBuilder atAll() {
        builder.append(AtAll.INSTANCE);
        return this;
    }

    public MessageBuilder image(Contact contact, String file, String url) {
        try {
            if (ImageUtils.notExist(file)) {
                ImageUtils.downloadImage(file, url);
            }
        } catch (IOException e) {
            builder.append(new PlainText("下载错误：" + e.getMessage()));
            e.printStackTrace();
        }

        String path2File = ImageUtils.getImageHome().concat(file);

        return image(contact, path2File);
    }

    public MessageBuilder image(Contact contact, String path2File){
        try(ExternalResource imageRes = ExternalResource.create(new File(path2File))) {
            Image image = contact.uploadImage(imageRes);
            builder.append(image);
        } catch (IOException e) {
            builder.append(new PlainText("发送错误：" + e.getMessage()));
            e.printStackTrace();
        }
        return this;
    }

    public MessageChain voice(AudioSupported audioSupported, String path2File) {
        Audio audio = audioSupported.uploadAudio(ExternalResource.create(new File(path2File)));
        return builder.append(audio).build();
    }

    public MessageChain build() {
        return builder.build();
    }

}
