package com.ecirsulga.homework5.n11bootcamp.usr.dto;

import lombok.Data;


@Data
public class UsrUserSaveRequestDto {

    private Long id;
    private String name;
    private String username;
    private String password;
}
