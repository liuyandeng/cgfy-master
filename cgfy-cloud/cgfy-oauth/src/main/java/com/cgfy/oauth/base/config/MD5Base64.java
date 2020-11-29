package com.cgfy.oauth.base.config;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.util.Base64;

/**
 * 自定义PasswordEncoder
 */
public class MD5Base64  implements PasswordEncoder {


    public String encode(CharSequence rawPassword) {
        String raw = rawPassword.toString();
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            // MD5 编码
            byte[] result = md5.digest(raw.getBytes("utf-8"));
            // BASE64 编码
            String base64Code = Base64.getEncoder().encodeToString(result);

            System.out.println(base64Code);

            return base64Code;
        }catch (Exception e){
            return e.getMessage();
        }
    }
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String raw = rawPassword.toString();
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            // MD5 编码
            byte[] result = md5.digest(raw.getBytes("utf-8"));
            // BASE64 编码
            String base64Code = Base64.getEncoder().encodeToString(result);

            return base64Code.equals(encodedPassword);
        }catch (Exception e){
            return false;
        }

    }
}
