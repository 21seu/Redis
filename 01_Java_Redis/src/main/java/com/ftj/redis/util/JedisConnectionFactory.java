package com.ftj.redis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by fengtj on 2022/5/21 0:13
 */
public class JedisConnectionFactory {

    private static final JedisPool JEDIS_POOL;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        //最大连接数
        config.setMaxTotal(8);
        //最大空闲连接
        config.setMaxIdle(8);
        //最小空闲连接（超过一段时间，连接没有被访问，那么会被释放直到为0为止）
        config.setMinIdle(0);
        //没有连接时，等待连接的时间，默认-1 无限等待
        config.setMaxWaitMillis(1000);
        JEDIS_POOL = new JedisPool(config,
                "localhost",
                6379,
                1000);
    }

    public static Jedis getJedis(){
        return JEDIS_POOL.getResource();
    }
}
