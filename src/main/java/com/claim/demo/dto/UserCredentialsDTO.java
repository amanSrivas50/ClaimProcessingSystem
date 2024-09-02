package com.claim.demo.dto;

import lombok.Data;

@Data
public class UserCredentialsDTO {
    private String username;
    private String password;

    public UserCredentialsDTO() {}

    public UserCredentialsDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
