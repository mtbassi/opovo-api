package br.com.mtbassi.opovo.api.modules.journalists.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private String token;
}
