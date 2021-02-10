package com.vwmin.miraivwmin.event;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.message.data.ForwardMessage;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/2/4 14:04
 */
public class ForwardMessageBuilder {
    private final Bot bot;
    private final net.mamoe.mirai.message.data.ForwardMessageBuilder builder;
    private MessageNode current;

    public ForwardMessageBuilder(Bot bot, Contact contact){
        this.bot = bot;
        this.builder = new net.mamoe.mirai.message.data.ForwardMessageBuilder(contact);
    }

    public MessageNode says(){
        if (current != null){
            builder.says(bot, current.builder.build());
        }
        current = new MessageNode(this);
        return current;
    }


    public ForwardMessage build(){
        if (current != null){
            builder.says(bot, current.builder.build());
        }
        return builder.build();
    }



    public static class MessageNode{
        private final ForwardMessageBuilder parent;
        private final MessageBuilder builder;
        private MessageNode(ForwardMessageBuilder parent){
            this.parent = parent;
            this.builder = new MessageBuilder();
        }

        public MessageNode plaintext(String text){
            builder.plaintext(text);
            return this;
        }

        public MessageNode image(Contact contact, String file, String url){
            builder.image(contact, file, url);
            return this;
        }

        public MessageNode says(){
            return parent.says();
        }


        public ForwardMessage build(){
            return parent.build();
        }

    }
}
