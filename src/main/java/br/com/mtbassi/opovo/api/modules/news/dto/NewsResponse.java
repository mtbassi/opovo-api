package br.com.mtbassi.opovo.api.modules.news.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class NewsResponse {

    @NotNull
    private UUID id;

    @NotNull
    private UUID idJournalist;

    @NotNull
    private UUID idNewsType;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String newsBody;

    private String featuredImage;
}
