package br.com.mtbassi.opovo.api.modules.news.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity(name = "news")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(name = "journalist_id")
    private UUID idJournalist;

    @NotNull
    @Column(name = "news_type_id")
    private UUID idNewsType;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String newsBody;

    private String featuredImage;
}
