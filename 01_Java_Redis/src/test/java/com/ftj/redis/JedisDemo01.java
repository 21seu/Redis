package com.ftj.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
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
        //添加
        jedis.set("name", "lucy");

        //获取
        jedis.get("name");

        //设置对各key-value
        jedis.mset("k1", "v1", "k2", "v2", "k3", "v3", "k4", "v4", "k5", "v5");
        List<String> mget = jedis.mget("k1", "k2", "k3", "k4", "k5");

        //list
        jedis.lpush("lkey1", "lucy", "mary", "jack");
        List<String> lkey1 = jedis.lrange("lkey1", 0, -1);
        lkey1.forEach(l -> System.out.println(l));
        System.out.println(mget);//[v1, v2, v3, v4, v5]

        Set<String> keys = jedis.keys("*");
        keys.forEach(k -> System.out.println(k));

    }

    @Test
    public void demo02() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //set
        jedis.sadd("names", "Bob", "Amy", "Amy");
        Set<String> name = jedis.smembers("names");
        System.out.println(name);
        //删除
        jedis.srem("names");
    }

    @Test
    public void demo03() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //hash
        jedis.hset("users", "age", "20");
        System.out.println(jedis.hget("users", "age"));//20
    }

    @Test
    public void demo04() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //zset
        jedis.zadd("china", 100d, "shanghai");
        System.out.println(jedis.zrange("china", 0, -1));//[shanghai]
    }
}
