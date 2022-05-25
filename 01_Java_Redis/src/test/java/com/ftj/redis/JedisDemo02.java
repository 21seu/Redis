package com.ftj.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by fengtj on 2022/5/25 23:02
 */
public class JedisDemo02 {

    private Jedis jedis;

    @Before
    public void before(){
        jedis = new Jedis("localhost",6379);
    }

    //测试key相关
    @Test
    public void testKeys(){
        //删除一个key
        jedis.del("name");
        //删除多个key
        jedis.del("name","age");
        //判断一个key是否存在
        Boolean name = jedis.exists("name");

        //设置一个key超时时间
        Long age = jedis.expire("age", 100);
        Long name1 = jedis.expireAt("name", 1000);

        //查看一个key超时时间ttl
        Long age1 = jedis.ttl("age");

        //随机获取一个Key
        String s = jedis.randomKey();
        //修改key名称
        String rename = jedis.rename("name", "newName");
        //查看key对应值的类型
        String name2 = jedis.type("name");
    }
}
