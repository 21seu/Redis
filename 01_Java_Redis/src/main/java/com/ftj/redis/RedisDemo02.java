package com.ftj.redis;

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
        System.out.println(getCode());
    }


    /**
     * 生成6位验证码
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
    public static void verify(){

    }
}
