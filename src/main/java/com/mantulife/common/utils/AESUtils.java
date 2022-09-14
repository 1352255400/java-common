package com.mantulife.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;


/**
 * author W_wang
 * ReqFilter AES加密128位CBC模式工具类
 * email 1352255400@qq.com
 * date 2021-10-11 10:08:15
 */
@Slf4j
@Component
public class AESUtils {

    //解密密钥(自行随机生成)
    //密钥key 16位
    private static String KEY;
    //向量iv 16位
    private static String IV;

    // "算法/模式/补码方式" AES/CBC/PKCS5Padding AES/ECB/PKCS5Padding
    private static final String ALGORITHMSTR = "AES/CBC/PKCS5Padding";
    // 加密算法
    private static final String AES = "AES";
    // 字符集
    private static final String CHARSETNAME = "utf-8";

    //认证密钥(自行随机生成)
    //AccessKey
    public static final String ACCESSKEY = "s2ip9g3y3bjr5zz7ws6kjgx3ysr82zzw";
    //SecretKey
    public static final String SECRETKEY = "uv8zr0uen7aim8m7umcuooqzdv8cbvtf";

    @Value("${aes.key:''}")
    public void setKEY(String key) {
        AESUtils.KEY = key;
    }

    //向量iv 16位
    @Value("${aes.iv:''}")
    public void setDemo(String iv) {
        AESUtils.IV = iv;
    }


    /**
     * author W_wang
     * encrypt 加密
     * email 1352255400@qq.com
     * date 2021-10-11 10:08:15
     */
    public static String encrypt(String key, String iv, String content) {
        log.info("自定义key:{},iv:{}",key,iv);
        try {
            byte[] raw = key.getBytes(CHARSETNAME);
            // 指定加密算法
            SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
            //"算法/模式/补码方式"
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            //使用CBC模式，需要一个向量iv，可增加加密算法的强度
            IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ips);
            byte[] encrypted = cipher.doFinal(content.getBytes());
            // 转换换行符（其他语言解密失败的问题）
            return new BASE64Encoder().encode(encrypted).replaceAll("[\\s*\t\n\r]", "");
        } catch (Exception e) {
            log.info("加密失败:" + content);
            log.info(e.getMessage());
            return null;
        }
    }

    /**
     * author W_wang
     * encrypt 加密
     * email 1352255400@qq.com
     * date 2021-10-11 10:08:15
     */
    public static String encrypt(String content) {
        try {
            byte[] raw = KEY.getBytes(CHARSETNAME);
            // 指定加密算法
            SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
            //"算法/模式/补码方式"
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            //使用CBC模式，需要一个向量iv，可增加加密算法的强度
            IvParameterSpec ips = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ips);
            byte[] encrypted = cipher.doFinal(content.getBytes());
            // 转换换行符（其他语言解密失败的问题）
            return new BASE64Encoder().encode(encrypted).replaceAll("[\\s*\t\n\r]", "");
        } catch (Exception e) {
            log.info("加密失败:" + content);
            log.info(e.getMessage());
            return null;
        }
    }


    /**
     * author W_wang
     * decrypt 解密
     * email 1352255400@qq.com
     * date 2021-10-11 10:08:15
     */
    public static String decrypt(String content) {
        try {
            log.info("加解密KEY:" + KEY);
            log.info("加解密IV:" + IV);
            byte[] raw = KEY.getBytes(CHARSETNAME);
            // 指定加密算法
            SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
            //"算法/模式/补码方式"
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            //使用CBC模式，需要一个向量iv，可增加加密算法的强度
            IvParameterSpec ips = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ips);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(content);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString;
        } catch (Exception e) {
            log.info("解密失败:" + e.getMessage());
            return null;
        }
    }


    /**
     * author W_wang
     * decrypt 解密(指定key + iv)
     * email 1352255400@qq.com
     * date 2021-10-11 10:08:15
     */
    public static String decrypt(String key, String iv, String content) {
        try {
            log.info("加解密KEY:" + key);
            log.info("加解密IV:" + iv);
            byte[] raw = key.getBytes(CHARSETNAME);
            // 指定加密算法
            SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
            //"算法/模式/补码方式"
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            //使用CBC模式，需要一个向量iv，可增加加密算法的强度
            IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ips);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(content);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString;
        } catch (Exception e) {
            log.info("解密失败:" + e.getMessage());
            return null;
        }
    }


    /**
     * author W_wang
     * getSign 获取认证签名(身份认证需要)
     * email 1352255400@qq.com
     * date 2021-10-11 10:08:15
     */
    public static String getSign(String currentTime) {
        String sign = "";
        Map<String, Object> map = new HashMap<>();
        map.put("ak", ACCESSKEY);
        map.put("sk", SECRETKEY);
        map.put("ts", currentTime);
        //获取 参数字典排序后字符串
        String decrypt = getOrderMap(map);
        try {
            //指定sha1算法
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(decrypt.getBytes());
            //获取字节数组
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            // 字节数组转换为十六进制数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            sign = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sign;
    }


    /**
     * author W_wang
     * getOrderMap 获取参数的字典排序
     * email 1352255400@qq.com
     * date 2021-10-11 10:08:15
     */
    private static String getOrderMap(Map<String, Object> maps) {
        List<String> paramNames = new ArrayList<>();
        for (Map.Entry<String, Object> entry : maps.entrySet()) {
            paramNames.add(entry.getValue().toString());
        }
        Collections.sort(paramNames);
        StringBuilder paramStr = new StringBuilder();
        for (String paramName : paramNames) {
            paramStr.append(paramName);
        }
        return paramStr.toString();
    }

    public static void main(String[] args) throws Exception {
        String content = "需要加密的内容";
        String key = "bsQiKzQAilzx6QqXC6eAKIQ7M67AvMpC";
        String iv = "idFodfcoLcAoY6Nw2EmgLgTwoEWAFHlBd9lbvUAKk89OtBs1YkAnSBBEPVeo1UCr";
        //加密
        String ensl = AESUtils.encrypt(key, iv, content);
        log.info("加密后：" + ensl);
//        //解密
//        String desl = AESUtils.decrypt(key, iv, ensl);
//        log.info("解密后：" + desl);
    }
}