package com.vwmin.miraivwmin.bot;

import com.vwmin.miraivwmin.pixiv.PixivApi;
import com.vwmin.miraivwmin.setu.SetuApi;
import com.vwmin.miraivwmin.saucenao.SaucenaoApi;
import com.vwmin.restproxy.RestProxy;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/7 13:47
 */
@Slf4j
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "bot")
public class AppConfig {
    private String pixivApi;
    private String imageHome;

    @Bean("normalRestTemplate")
    public RestTemplate normalRestTemplate(){
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        HttpClient httpClient = HttpClientBuilder.create()
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();
        factory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(factory);
        return restTemplate;
    }

    @Bean
    public SetuApi setuApi(@Qualifier("normalRestTemplate") RestTemplate restTemplate){
        final String setuUrl = "https://api.lolicon.app";
        return new RestProxy<>(setuUrl, SetuApi.class, restTemplate).getApi();
    }

    @Bean
    public SaucenaoApi saucenaoApi(@Qualifier("normalRestTemplate") RestTemplate restTemplate){
        final String saucenaoUrl = "http://saucenao.com";
        return new RestProxy<>(saucenaoUrl, SaucenaoApi.class, restTemplate).getApi();
    }

    @Bean
    public PixivApi pixivApi(@Qualifier("normalRestTemplate") RestTemplate restTemplate){
        return new RestProxy<>(pixivApi, PixivApi.class, restTemplate).getApi();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
        GenericJackson2JsonRedisSerializer objectSerializer = new GenericJackson2JsonRedisSerializer();
        StringRedisSerializer stringSerializer = new StringRedisSerializer();

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
//        redisTemplate.setKeySerializer(stringSerializer);
//        redisTemplate.setHashKeySerializer(stringSerializer);
//        redisTemplate.setValueSerializer(objectSerializer);
//        redisTemplate.setHashValueSerializer(objectSerializer);
        redisTemplate.setDefaultSerializer(objectSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
