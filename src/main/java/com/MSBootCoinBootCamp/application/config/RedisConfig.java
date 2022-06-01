package com.MSBootCoinBootCamp.application.config;

import com.MSBootCoinBootCamp.domain.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Slf4j
public class RedisConfig {


    @Bean
    public ReactiveRedisConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory("localhost", 6379);
    }

    @Bean
    public ReactiveRedisTemplate<String, Post> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
        return new ReactiveRedisTemplate<String, Post>(
                factory,
                RedisSerializationContext.fromSerializer(new Jackson2JsonRedisSerializer(Post.class))
        );
    }
}