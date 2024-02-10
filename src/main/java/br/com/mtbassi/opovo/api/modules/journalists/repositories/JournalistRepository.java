package br.com.mtbassi.opovo.api.modules.journalists.repositories;

import br.com.mtbassi.opovo.api.modules.journalists.entities.JournalistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface JournalistRepository extends JpaRepository<JournalistEntity, UUID> {

    UserDetails findByEmail(String email);
}