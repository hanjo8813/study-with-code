package com.example.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Likes {

    private int likeId;
    private char likeYn;
    private LocalDateTime insertDate;

    private int memberId;
    private int boardId;
}
