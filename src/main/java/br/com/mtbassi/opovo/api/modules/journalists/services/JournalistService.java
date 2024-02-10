package br.com.mtbassi.opovo.api.modules.journalists.services;

import br.com.mtbassi.opovo.api.modules.journalists.dto.JournalistRequest;
import br.com.mtbassi.opovo.api.modules.journalists.dto.JournalistResponse;
import br.com.mtbassi.opovo.api.modules.journalists.entities.JournalistEntity;
import br.com.mtbassi.opovo.api.modules.journalists.repositories.JournalistRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JournalistService {

    private final JournalistRepository repository;
    private final ModelMapper modelMapper;

    public JournalistResponse register(JournalistRequest data) {
        var journalist = this.repository.save(modelMapper.map(data, JournalistEntity.class));
        return modelMapper.map(journalist, JournalistResponse.class);
    }

    public UserDetails loadUserByUsername(String email){
        return this.repository.findByEmail(email);
    }
}