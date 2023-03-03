package com.vwmin.miraivwmin.ping;

import com.vwmin.miraivwmin.core.CommandController;
import com.vwmin.miraivwmin.core.Reply;
import com.vwmin.miraivwmin.core.message.MessageBuilder;
import com.vwmin.miraivwmin.util.EmptyCommand;
import com.vwmin.restproxy.RestProxy;
import com.vwmin.restproxy.annotations.GET;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.client.RestTemplate;

@CommandController(bind = "proxy")
public class TestProxy implements Reply<EmptyCommand> {

    private final RestTemplate rest;

    public TestProxy(@Qualifier("normalRestTemplate") RestTemplate restTemplate){
        this.rest = restTemplate;
    }


    @Override
    public Message reply(EmptyCommand command, Contact subject, User sender) {
        String url = "https://www.google.com";
        TestProxyApi api = new RestProxy<>(url, TestProxyApi.class,  rest).getApi();

        return new MessageBuilder().plaintext(api.get()).build();
    }
}
