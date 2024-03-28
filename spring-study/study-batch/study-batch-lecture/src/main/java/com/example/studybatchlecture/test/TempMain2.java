package com.example.studybatchlecture.test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TempMain2 {

    public static void main(String[] args) {

        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);

        ZonedDateTime asiaNow = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println(asiaNow);

        ZonedDateTime utcNow = ZonedDateTime.now(ZoneOffset.UTC);
        System.out.println(utcNow);





        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

        // zdt -> str
        System.out.println("-------------");

        String formated = now.format(formatter);
        System.out.println(formated);
        System.out.println(ZonedDateTime.parse(now.format(formatter), formatter));


        // str -> zdt
//
//        String datetimeString = "2024-01-18T13:20:13.571031+09:00[Asia/Seoul]";
//        ZonedDateTime parse = ZonedDateTime.parse(datetimeString, formatter);
//        System.out.println(parse);

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
//        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.parse(datetimeString, formatter), ZoneId.of("Asia/Seoul"));
//        System.out.println(zonedDateTime);

        // 절삭

        System.out.println(now.truncatedTo(ChronoUnit.SECONDS));

    }
}
