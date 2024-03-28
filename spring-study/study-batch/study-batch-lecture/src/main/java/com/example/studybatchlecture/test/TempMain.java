package com.example.studybatchlecture.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;

public class TempMain {

    public static void main(String[] args) {
        List<Long> memberNos = new ArrayList<>();
        for (int i = 1; i < 300; i++) {
            memberNos.add(100000000L + i);
        }

        String str = memberNos.toString();
        System.out.println(str);
        System.out.println(str.getBytes().length);

        // ------

        List<String> strMemberNos = new ArrayList<>();
        for(Long memberNo : memberNos) {
            strMemberNos.add("[\"java.lang.Long\"" + "," + memberNo + "]");
        }

        String serializedStr = strMemberNos.toString();
        System.out.println(serializedStr);
        System.out.println(serializedStr.getBytes().length);



//        String encode = Base64.getEncoder().encodeToString(str.getBytes());
//
//        System.out.println(encode);
//        System.out.println(encode.length());
//
//        String decode = new String(Base64.getDecoder().decode(encode));
//
//        System.out.println(decode);
//
//        System.out.println(decode.equals(str));
    }
}
