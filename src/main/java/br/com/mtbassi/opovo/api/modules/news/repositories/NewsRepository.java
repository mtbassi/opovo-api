package br.com.mtbassi.opovo.api.modules.news.repositories;

import br.com.mtbassi.opovo.api.modules.news.entities.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NewsRepository extends JpaRepository<NewsEntity, UUID> {

    Optional<NewsEntity> findByIdAndIdJournalist(UUID id, UUID idJournalist);

    List<NewsEntity> findByIdJournalist(UUID idJournalist);

    List<NewsEntity> findByIdNewsType(UUID id);
}