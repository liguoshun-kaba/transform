package com.kaba.transform.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @program: transform
 * @description:
 * @author: lgs
 * @create: 2020/8/8 16:05
 **/
public class MyStringUtils {

    public final static GenIdService genIdService = new GenIdService();


    /**
     * 生成指定长度的字符串， 包含字母数字
     * */
    public static String randomStr(int size){
        return RandomStringUtils.randomAlphanumeric(size);
    }


    public static String randomStr(){
        return randomStr(6);
    }

    /**
     *  生成不重复的token
     * */
    public static String createNoRepeatStr(){
        return genIdService.nextStrKeyId();
    }

    /**
     * 生成指定位数的数字字符串
     * */
    public static String randomNumberStr(int size) {
        return RandomStringUtils.randomNumeric(size);
    }



}
