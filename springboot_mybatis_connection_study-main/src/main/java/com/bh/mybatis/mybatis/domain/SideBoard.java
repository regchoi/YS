package com.bh.mybatis.mybatis.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class SideBoard {
    private int sideboardId;
    private String category;
    private String content;
    private int mainboardId;
    private int memberId;

    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    @Override
    public String   toString() {
        return "SideBoard{" +
                "sideboardId=" + sideboardId +
                ", category='" + category + '\'' +
                ", content='" + content + '\'' +
                ", mainboardId=" + mainboardId +
                ", memberId=" + memberId +
                ", createDate=" + createDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
