package br.com.mtbassi.opovo.api.modules.journalists.services;

import br.com.mtbassi.opovo.api.modules.commons.utils.TokenUtils;
import br.com.mtbassi.opovo.api.modules.journalists.dto.JournalistRequest;
import br.com.mtbassi.opovo.api.modules.journalists.dto.JournalistResponse;
import br.com.mtbassi.opovo.api.modules.journalists.entities.JournalistEntity;
import br.com.mtbassi.opovo.api.modules.journalists.repositories.JournalistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JournalistService {

    private final JournalistRepository repository;
    private final ModelMapper modelMapper;

    public JournalistResponse register(JournalistRequest data) {
        try {
            data.setPassword(this.encryptedPassword(data.getPassword()));
            log.info("Attempting to register journalist: {}", data.getEmail());
            var journalist = this.repository.save(modelMapper.map(data, JournalistEntity.class));
            log.info("Successfully registered journalist with ID {}", journalist.getId());
            return modelMapper.map(journalist, JournalistResponse.class);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(e.getMessage(), e);
        }
    }

    public JournalistResponse me() {
        return modelMapper.map(TokenUtils.getPrincipal(), JournalistResponse.class);
    }

    public UserDetails loadUserByUsername(String email) {
        return this.repository.findByEmail(email);
    }

    private String encryptedPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}