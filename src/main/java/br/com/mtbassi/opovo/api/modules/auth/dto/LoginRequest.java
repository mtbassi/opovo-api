package br.com.mtbassi.opovo.api.modules.auth.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;
}
