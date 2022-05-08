package com.example.springbootcloud.global;
import java.util.Base64;

public class Encoding {
    public static String encode(String pass){
        Base64.Encoder enc = Base64.getEncoder();
        return enc.encodeToString(pass.getBytes());
    }

    public static String decode(String pass){
        Base64.Decoder dec = Base64.getDecoder();
        return new String(dec.decode(pass));
    }
}

