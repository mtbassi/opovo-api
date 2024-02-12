package br.com.mtbassi.opovo.api.modules.news_types.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity(name = "news_types")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(name = "journalist_id")
    private UUID idJournalist;

    @NotBlank
    @Column(name = "type_name")
    private String name;
}