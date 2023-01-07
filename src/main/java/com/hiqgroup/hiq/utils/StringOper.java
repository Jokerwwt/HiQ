package com.hiqgroup.hiq.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class StringOper {

    public static String base64Encode(String workerid, String usrname) {
        try {
            String result = Base64.getEncoder().encodeToString(
                    (workerid+"|" + System.currentTimeMillis() + "|" + usrname).getBytes("utf-8"));
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String MD5Encrypt(String password)
    {
        String pwd = "";
        // 加密后是一个字节类型的数组，这里要注意编码UTF8/Unicode等的选择　
        byte[] s = new byte[0];
        try {
            s = MessageDigest.getInstance("md5").digest(
                    password.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        pwd = new BigInteger(1, s).toString(16);
        for (int i = 0; i < 32 - pwd.length(); i++)
        {
            pwd = "0" + pwd;
        }
        return pwd.trim();
    }

    public static String addZeroForNum(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append("0").append(str);// 左补0
//                sb.append(str).append("0");//右补0
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }
}
