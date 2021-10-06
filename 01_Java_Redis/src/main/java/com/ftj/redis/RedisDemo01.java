package com.ftj.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Created by fengtj on 2021/9/25 9:49
 */
public class RedisDemo01 {

    public static void main(String[] args) {
        //1、创建jedis客户端对象
        Jedis jedis = new Jedis("", 6379);
        jedis.auth("");
        //选择使用一个库，这一行不写，默认0号库
        jedis.select(0);
        Set<String> keys = jedis.keys("*");
        keys.forEach(key -> System.out.println("key = " + key));
        //释放资源
        jedis.close();
    }
}
