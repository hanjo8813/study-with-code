package com.example.studyprom;

import io.micrometer.core.instrument.Tag;
import java.util.List;

public class CustomTag {

    public static List<Tag> tag1 = List.of(
            // 라벨은 카멜케이스
            Tag.of("label1", "1"),
            Tag.of("label2", "2")
    );

    public static List<Tag> tag2 = List.of(
            Tag.of("label3", "3")
    );

    public static List<Tag> tag3 = List.of(
            Tag.of("label1", "1"),
            Tag.of("label2", "2"),
            Tag.of("label3", "3")
    );

    public static List<Tag> tag4 = List.of(
            Tag.of("label1", "11"),
            Tag.of("label2", "22")
    );

    public static List<Tag> tag5 = List.of(
            Tag.of("label1", "11")
    );
}
