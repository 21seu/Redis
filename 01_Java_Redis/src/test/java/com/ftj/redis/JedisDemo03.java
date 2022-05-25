package com.ftj.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by fengtj on 2022/5/25 23:11
 */
public class JedisDemo03 {

    private Jedis jedis;

    @Before
    public void before() {
        jedis = new Jedis("localhost", 6379);
    }

    //测试String相关
    @Test
    public void testString() {
        //set
        jedis.set("s1", "小刘");
        //get
        jedis.get("s1");
        //mset
        jedis.mset("content", "好人", "address", "hangzhou");
        //mget
        List<String> mget = jedis.mget("s1", "content", "address");
        //getset
        jedis.getSet("name", "晓峰");

    }
}
