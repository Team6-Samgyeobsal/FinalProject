package com.samgyeobsal.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
@Slf4j
public class RedisTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void insertKeyToRedis(){
        redisTemplate.opsForValue().set("user@gmail.com","1111");

        String value = (String) redisTemplate.opsForValue().get("user@gmail.com");
        log.info("value = {}", value);

    }




}
