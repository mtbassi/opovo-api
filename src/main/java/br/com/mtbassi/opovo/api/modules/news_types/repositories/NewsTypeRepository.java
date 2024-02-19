package br.com.mtbassi.opovo.api.modules.news_types.repositories;

import br.com.mtbassi.opovo.api.modules.news_types.entities.NewsTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NewsTypeRepository extends JpaRepository<NewsTypeEntity, UUID> {

    Optional<NewsTypeEntity> findByIdAndIdJournalist(UUID uuid, UUID idJournalist);

    List<NewsTypeEntity> findByIdJournalist(UUID idJournalist);
}
