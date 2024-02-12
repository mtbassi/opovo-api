package br.com.mtbassi.opovo.api.modules.news_types.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NewsTypeRequest {

    @NotBlank
    private String name;
}