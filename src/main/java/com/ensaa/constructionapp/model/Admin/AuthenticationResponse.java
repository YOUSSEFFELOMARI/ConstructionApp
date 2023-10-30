package com.ensaa.constructionapp.model.Admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponse {
    String token;
    String msg;
}
