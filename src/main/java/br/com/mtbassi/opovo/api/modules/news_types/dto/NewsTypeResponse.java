package br.com.mtbassi.opovo.api.modules.news_types.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class NewsTypeResponse {

    @NotNull
    private UUID id;

    @NotNull
    private UUID idJournalist;

    @NotBlank
    private String name;
}