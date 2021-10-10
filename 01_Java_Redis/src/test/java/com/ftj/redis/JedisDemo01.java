package com.ftj.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Created by fengtj on 2021/10/10 23:36
 */
public class JedisDemo01 {

    /**
     * 操作key
     */
    @Test
    public void demo01() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        Set<String> keys = jedis.keys("*");
        keys.forEach(k -> System.out.println(k));

    }
}
