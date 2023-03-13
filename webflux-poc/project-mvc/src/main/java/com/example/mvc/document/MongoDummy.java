package com.example.mvc.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@Document(collection = "testCollection")
public class MongoDummy {

    @Id
    private String id;
    private int num;
    private String str;
}
