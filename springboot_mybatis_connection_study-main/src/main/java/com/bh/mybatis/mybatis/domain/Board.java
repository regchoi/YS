package com.bh.mybatis.mybatis.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Board {
    private Long board_id;

    private String category;
    private String title;
    private String content;
    private Long parentId;

    private List<Member> memberList;

    private LocalDateTime createDate;
    private LocalDateTime update;
}
