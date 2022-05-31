package com.bh.mybatis.mybatis.domain;

import lombok.*;

@Getter @Setter
public class Member {
    private int memberId;
    private String id;
    private String password;
    private String name;

    public Member() {
    }

    public Member(int memberId, String id, String password, String name) {
        this.memberId = memberId;
        this.id = id;
        this.password = password;
        this.name = name;
    }
}
