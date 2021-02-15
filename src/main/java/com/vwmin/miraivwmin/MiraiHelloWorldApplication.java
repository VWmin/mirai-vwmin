package com.vwmin.miraivwmin;

import com.sun.corba.se.spi.ior.IdentifiableFactory;
import com.vwmin.miraivwmin.event.MyEventHandler;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.internal.utils.io.Utils;
import net.mamoe.mirai.utils.BotConfiguration;
import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Properties;

@Slf4j
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

        ConfigurableApplicationContext applicationContext = SpringApplication.run(MiraiHelloWorldApplication.class, args);


        Bot bot = BotFactory.INSTANCE.newBot(212746897, "justmiss.qq", new BotConfiguration() {
            {
                //保存设备信息到文件
                fileBasedDeviceInfo("deviceInfo.json");
                //修改登录协议
                setProtocol(MiraiProtocol.ANDROID_PHONE);
                // setLoginSolver();
                // setBotLoggerSupplier();

                // noNetworkLog();

            }
        });

        bot.login();
        bot.getEventChannel().registerListenerHost(new MyEventHandler(applicationContext, bot));

        applicationContext.getBeanFactory().registerSingleton("bot", bot);

        if(applicationContext.getBean("bot") instanceof Bot){
            log.info("Bot injection success.");
        }else {
            log.error("Bot injection failed.");
        }


        bot.join();
    }

}
