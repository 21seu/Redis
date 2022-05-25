package com.ftj.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ListPosition;

import java.util.List;

/**
 * Created by fengtj on 2022/5/25 23:16
 */
public class JedisDemo04 {

    private Jedis jedis;

    @Before
    public void before() {
        jedis = new Jedis("localhost", 6379);
    }

    //测试List相关
    @Test
    public void testList() {
        //lpush
        jedis.lpush("names1", "a", "b", "c", "d");

        //rpush
        jedis.rpush("names1", "e");

        //lrange
        List<String> name1 = jedis.lrange("names1", 0, -1);
        System.out.println(name1);
        name1.forEach(name -> System.out.println("name = " + name));
        //lpop rpop
        String names1 = jedis.lpop("names1");

        //llen
        Long len = jedis.llen("names1");
        //lset
        jedis.lset("names1", 0, "aa");
        //linsert
        jedis.linsert("names1", ListPosition.BEFORE, "b", "ab");
    }
}
