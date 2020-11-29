package com.cgfy.oauth.base.config;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.util.Random;

public class MD5PasswordEncoder implements PasswordEncoder {


    @Override
    public String encode(CharSequence rawPassword) {
        Random random = new Random();
        StringBuilder sBuilder = new StringBuilder(16);
        sBuilder.append(random.nextInt(99999999)).append(random.nextInt(99999999));
        int len = sBuilder.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sBuilder.append("0");
            }
        }
        //摘要内容:密码与"加盐"值后按规则掺入48的字符之后形成一个字符串
        String Salt = sBuilder.toString();
        rawPassword = md5Hex(rawPassword + Salt);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = rawPassword.charAt(i / 3 * 2);
            char c = Salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = rawPassword.charAt(i / 3 * 2 + 1);
        }
        return String.valueOf(cs);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = encodedPassword.charAt(i);
            cs1[i / 3 * 2 + 1] = encodedPassword.charAt(i + 2);
            cs2[i / 3] = encodedPassword.charAt(i + 1);
        }
        String Salt = new String(cs2);
        return md5Hex(rawPassword + Salt).equals(String.valueOf(cs1));
    }

    /**
     * MD5摘要计算
     * @param str
     * @return
     */
    public static String md5Hex(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(str.getBytes());
            return new String(new Hex().encode(digest));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
            return "";
        }
    }
}
