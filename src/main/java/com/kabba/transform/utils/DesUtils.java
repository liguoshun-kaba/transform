package com.kabba.transform.utils;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.Security;

/**
 * @projectName: kabba-platform
 * @author: user
 * @description: 加密类
 * @dateTime: 2020/6/12 10:08
 */
public class DesUtils {

    /** 字符串默认键值     */
    private static String strDefaultKey = "national";

    /** 加密工具     */
    private Cipher encryptCipher = null;

    /** 解密工具     */
    private Cipher decryptCipher = null;

    /**
     * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
     * hexStr2ByteArr(String strIn) 互为可逆的转换过程
     *
     */
    public static String byteArr2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    /**
     * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
     * 互为可逆的转换过程
     *
     */
    public static byte[] hexStr2ByteArr(String strIn) throws Exception {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;

        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }

    /**
     * 默认构造方法，使用默认密钥
     *
     * @throws Exception
     */
    public DesUtils() throws Exception {
        this(strDefaultKey);
    }

    /**
     * 指定密钥构造方法
     *
     * @param strKey
     *            指定的密钥
     * @throws Exception
     */
    public DesUtils(String strKey){
        try{
            Security.addProvider(new com.sun.crypto.provider.SunJCE());
            Key key = getKey(strKey.getBytes());

            encryptCipher = Cipher.getInstance("DES");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);

            decryptCipher = Cipher.getInstance("DES");
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @param noMean 无意义的参数，用来区别无参的构造
     * 配合initKey()方式实现动态密钥
     * */
    public DesUtils(int noMean){

    }

    public void initKey(String strKey){
        try{
            Security.addProvider(new com.sun.crypto.provider.SunJCE());
            Key key = getKey(strKey.getBytes());

            encryptCipher = Cipher.getInstance("DES");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);

            decryptCipher = Cipher.getInstance("DES");
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 加密字节数组
     *
     * @param arrB
     *            需加密的字节数组
     * @return 加密后的字节数组
     * @throws Exception
     */
    public byte[] encrypt(byte[] arrB) throws Exception {
        return encryptCipher.doFinal(arrB);
    }

    /**
     * 加密字符串
     *
     * @param strIn
     *            需加密的字符串
     * @return 加密后的字符串
     * @throws Exception
     */
    public String encrypt(String strIn) {
        String msg = "";
        try {
            msg = byteArr2HexStr(encrypt(strIn.getBytes("utf-8")));   //这里必须指定编码，否则编码的时候可能会出现乱码
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 解密字节数组
     *
     * @param arrB
     *            需解密的字节数组
     * @return 解密后的字节数组
     * @throws Exception
     */
    public byte[] decrypt(byte[] arrB) throws Exception {
        return decryptCipher.doFinal(arrB);
    }

    /**
     * 解密字符串
     *
     * @param strIn
     *            需解密的字符串
     * @return 解密后的字符串
     * @throws Exception
     */
    public String decrypt(String strIn){
        String msg = "";
        try {
            //这里必须指定编码，否则在eclipse中启动tomcat和单独启动tomcat时解码出现乱码
            msg = new String(decrypt(hexStr2ByteArr(strIn)),"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
     *
     * @param arrBTmp
     *            构成该字符串的字节数组
     * @return 生成的密钥
     * @throws Exception
     */
    private Key getKey(byte[] arrBTmp) throws Exception {
        // 创建一个空的8位字节数组（默认值为0）
        byte[] arrB = new byte[8];

        // 将原始字节数组转换为8位
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }

        // 生成密钥
        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

        return key;
    }

    /**
     * main方法  。
     * @param args
     */
    public static void main(String[] args) {
        try {
            String test = "123456789中华人民共和国成立于哪一年呢？————（）():收到了房价快速的法律围殴让我尔透镜的收费乐山大佛就快了" +
                    "温柔徒儿提高了空间大飞哥尽量快点撒感觉凉快撒旦了罚款交水电费了加快速度发来即可";
            DesUtils des = new DesUtils("leemenz");//自定义密钥
            System.out.println("加密前的字符：" + test);
            System.out.println("加密后的字符：" + des.encrypt(test));
            System.out.println("解密后的字符：" + des.decrypt(des.encrypt(test)));

            //System.out.println("解密后的字符：" + des.decrypt("45bb7c8a9a699cd7f2bbe47f3c9a7c86"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
