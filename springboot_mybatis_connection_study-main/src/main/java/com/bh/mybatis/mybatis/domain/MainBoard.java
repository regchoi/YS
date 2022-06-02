package com.bh.mybatis.mybatis.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MainBoard {
    private int mainboardId;

    private String category;
    private String title;
    private String content;

    private int memberId;

    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
}
