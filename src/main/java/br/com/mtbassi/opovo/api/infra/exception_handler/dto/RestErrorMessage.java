package br.com.mtbassi.opovo.api.infra.exception_handler.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class RestErrorMessage {

    private Integer httpStatus;
    private String message;
}