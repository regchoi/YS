package com.bh.mybatis.mybatis.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JoinForm {
    private String id;
    private String password;
    private String checkPassword;
    private String name;

}
