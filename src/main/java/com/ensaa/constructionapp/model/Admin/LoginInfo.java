package com.ensaa.constructionapp.model.Admin;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginInfo {

    private String email;
    private String password;
}
