package com.ftj.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fengtj on 2021/11/4 0:26
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping
    public String testRedis01() {
        //设置值
        redisTemplate.opsForValue().set("myName", "ftj");
        //从reids获取值
        Object myName = redisTemplate.opsForValue().get("myName");
        return (String) myName;
    }

    @GetMapping("/user")
    public User testRedis02() {
        redisTemplate.opsForValue().set("user:001", new User(1L, "ftj", 25));
        User user = (User) redisTemplate.opsForValue().get("user:001");
        return user;
    }

    @GetMapping
    public String testRedis03() throws JsonProcessingException {
        User user = new User(2L, "lzg", 25);
        //设置值 手动序列化
        stringRedisTemplate.opsForValue().set("user:002", new ObjectMapper().writeValueAsString(user));
        //从reids获取值
        String val = stringRedisTemplate.opsForValue().get("user:002");
        //手动反序列化
        new ObjectMapper().readValue(val, User.class);
        return val;
    }
}
