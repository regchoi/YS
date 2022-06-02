package com.bh.mybatis.mybatis.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class SideBoard {
    private int sideboardId;
    private String title;
    private String content;
    private int parentboardId;
    private int memberId;

    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
}
