package com.yph.constant;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;

import java.math.BigDecimal;
import java.util.Random;

public class SystemConstant {

    public static final int ID = -1;

    //28分流
    public  static  final BigDecimal rewardRate = new BigDecimal("0.8");

    //所有的生命源,能量源总账户
    public  static  final  Integer lifeUserId = 24642;

    //所有的红包总账户
    public  static  final  Integer redUserId = 24643;

    //一团的人数
    public  static  final  int  groupCount = 33;  //33人一团 1人中奖   100人一团 3人中奖

    //系统随机数   
    public  static  final Random RANDOM_REDIS_OVERDUE = new Random();

    public  static  final  int BINGO_NUMBER = 1;

    //拼团开关控制
    public  static  final  String SPELL_CONTR_REDIS_KEY  = "SPELL_CONTR_REDIS_KEY";


    public  static RedisAtomicInteger redisAtomicInteger(RedisConnectionFactory redisConnectionFactory){
        RedisAtomicInteger redisAtomicInteger = new RedisAtomicInteger("SPELL_EXECUT_REDIS_KEY",redisConnectionFactory);
        return redisAtomicInteger;
    }



}


