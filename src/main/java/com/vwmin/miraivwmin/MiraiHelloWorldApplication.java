package com.vwmin.miraivwmin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.util.Properties;


@Slf4j
@EnableAsync
@SpringBootApplication
public class MiraiHelloWorldApplication {

    public static void main(String[] args) {


//        Properties prop = System.getProperties();
//        prop.put("proxySet", true);
//        prop.setProperty("socksProxyHost", "127.0.0.1");
//        prop.setProperty("socksProxyPort", "9888");
//        System.setProperty("http.proxyHost", "localhost");
//        System.setProperty("http.proxyPort", "9888");
//        System.setProperty("https.proxyHost", "localhost");
//        System.setProperty("https.proxyPort", "9888");

        SpringApplication.run(MiraiHelloWorldApplication.class, args);

    }

}
