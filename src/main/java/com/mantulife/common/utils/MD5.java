package com.mantulife.common.utils;

import com.mantulife.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * author W_wang
 * version V1.0
 * remark MD5 工具类
 * email 1352255400@qq.com
 * date 2020/8/4 17:22
 */
@Slf4j
public final class MD5 {

    /**
     * author W_wang
     * version V1.0
     * remark 自定义生成MD5
     * email 1352255400@qq.com
     * date 2020/8/4 17:22
     * @param strSrc strSrc
     * @return  return return
     */
    public static String encrypt(String strSrc) {
        strSrc += "mantulife";
        try {
            char hexChars[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                    '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            byte[] bytes = strSrc.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            bytes = md.digest();
            int j = bytes.length;
            char[] chars = new char[j * 2];
            int k = 0;
            for (int i = 0; i < bytes.length; i++) {
                byte b = bytes[i];
                chars[k++] = hexChars[b >>> 4 & 0xf];
                chars[k++] = hexChars[b & 0xf];
            }
            return new String(chars);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new BusinessException("MD5加密出错！！+" + e);
        }
    }


    /**
     * author W_wang
     * version V1.0
     * remark 通用MD5（兼容php）
     * email 1352255400@qq.com
     * date 2020/8/4 17:22
     * @param str str
     * @return  return return
     */
    public static String md5(String str) {
        String result = str;
        if (str != null) {
            //or “SHA-1”
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(str.getBytes());
                BigInteger hash = new BigInteger(1, md.digest());
                result = hash.toString(16);
                while (result.length() < 32) {
                    result = "0" + result;
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                log.info("生成MD5失败{}", e.getMessage());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        log.info(MD5.encrypt("111111"));
    }

}
