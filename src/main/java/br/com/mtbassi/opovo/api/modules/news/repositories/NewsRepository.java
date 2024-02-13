package br.com.mtbassi.opovo.api.modules.news.repositories;

import br.com.mtbassi.opovo.api.modules.news.entities.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NewsRepository extends JpaRepository<NewsEntity, UUID> {

    List<NewsEntity> findByIdNewsType(UUID id);
}