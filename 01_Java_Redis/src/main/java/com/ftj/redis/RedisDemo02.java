package com.ftj.redis;

import com.sun.deploy.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * Created by fengtj on 2021/10/13 23:05
 * redis模拟验证码案例：
 * 1、输入手机号，点击发送后随机生成6位数字码，2分钟有效
 * 2、输入验证码，点击验证，返回成功或失败
 * 3、每个手机号每天只能输入3次
 */
public class RedisDemo02 {

    public static void main(String[] args) {
        //模拟验证码发送
        //verify("15922881790");

        getRedisCode("15922881790","729997");
    }


    /**
     * 生成6位验证码
     *
     * @return
     */
    public static String getCode() {
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            code += random.nextInt(10);
        }
        return code;
    }

    /**
     * 每个手机每天只能发送三次，验证码放到redis中，设置过期时间
     */
    public static void verify(String phone) {
        //连接redis
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //手机发送次数key
        String countKey = "VerifyCode" + phone + ":count";
        //验证码key
        String codeKey = "VerifyCode" + phone + ":code";
        //每个手机每天只能发送3次
        if (jedis.get(countKey) == null) {
            //没有发送次数，则第一次发送，设置发送次数为1
            jedis.setex(codeKey, 24 * 60 * 60, "1");
        } else if (Integer.parseInt(jedis.get(countKey)) <= 2) {
            //发送次数+1
            jedis.incr(codeKey);
        } else if (Integer.parseInt(jedis.get(countKey)) > 2) {
            System.out.println("今天的放松次数已经超过3次");
            jedis.close();
            return;
        }

        //发送验证码放到redis
        String vcode = getCode();
        jedis.setex(codeKey, 120, vcode);
        jedis.close();
    }

    /**
     * 验证码校验
     *
     * @param phone
     * @param code
     */
    public static void getRedisCode(String phone, String code) {
        //连接redis
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //验证码key
        String codeKey = "VerifyCode" + phone + ":code";
        String redisCode = jedis.get(codeKey);
        if (redisCode != null && redisCode.equals(code)) {
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }
        jedis.close();
    }
}
