package br.com.mtbassi.opovo.api.modules.journalists.repositories;

import br.com.mtbassi.opovo.api.modules.journalists.entities.JournalistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JournalistRepository extends JpaRepository<JournalistEntity, UUID> {
}