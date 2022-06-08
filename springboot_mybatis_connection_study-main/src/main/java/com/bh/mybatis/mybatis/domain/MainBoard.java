package com.bh.mybatis.mybatis.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class MainBoard {

    private int mainboardId;

    private String category;
    private String title;
    private String content;

    private int view;

    private int memberId;
    private int sideboardId;
    private int replyId;

    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
}
