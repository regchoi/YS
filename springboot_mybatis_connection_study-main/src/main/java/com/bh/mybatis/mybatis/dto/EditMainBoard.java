package com.bh.mybatis.mybatis.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EditMainBoard {
    private int mainboardId;
    private String title;
    private String content;
    private LocalDateTime modifiedDate;
}
