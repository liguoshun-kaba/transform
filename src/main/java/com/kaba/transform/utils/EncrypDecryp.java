package com.kaba.transform.utils;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * @projectName: kabba-platform
 * @author: user
 * @description: 加解密类
 * @dateTime: 2020/6/12 10:09
 */
public class EncrypDecryp {

    private static final String INIT_KEY = "5a4c47040c26b70dfd7bb6b6";

    private final static DesUtils DES = new DesUtils(0);

    public static String byte2HexStr(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            String s = Integer.toHexString(b[i] & 0xFF);
            if (s.length() == 1) {
                sb.append("0");
            }

            sb.append(s.toLowerCase());
        }

        return sb.toString();
    }

    /**
     * md5加密
     */
    public static String md5Digest(String src) {
        // 定义数字签名方法, 可用：MD5, SHA-1
        String msg = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = md.digest(src.getBytes("utf-8"));
            msg = byte2HexStr(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 取uuid字符串
     */
    public static String getUuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 取uuid字符串的MD5加密后的字符串
     */
    public static String getUuidMd5() {
        return md5Digest(UUID.randomUUID().toString());
    }

    /**
     * 加密
     */
    public static String enCode(String content) {
        DES.initKey(INIT_KEY);
        return DES.encrypt(content);
    }

    /**
     * 解密
     */
    public static String deCode(String content) {
        DES.initKey(INIT_KEY);
        return DES.decrypt(content);
    }

    public static void main(String[] args){
        //b8d937c04cccd932a0a42bb6ec263947 WBkNrR
        String source = getUuid();

        System.out.println("source: " + source);
        String encode = enCode(source);
        System.out.println("encode: " + encode);
        String decode = deCode(encode);
        System.out.println("decode: " + decode);


        System.out.println(md5Digest("123456"));


    }

}
