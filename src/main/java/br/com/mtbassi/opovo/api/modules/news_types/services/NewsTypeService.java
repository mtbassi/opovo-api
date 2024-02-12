package br.com.mtbassi.opovo.api.modules.news_types.services;

import br.com.mtbassi.opovo.api.infra.security.TokenUtils;
import br.com.mtbassi.opovo.api.modules.news_types.dto.NewsTypeRequest;
import br.com.mtbassi.opovo.api.modules.news_types.dto.NewsTypeResponse;
import br.com.mtbassi.opovo.api.modules.news_types.entities.NewsTypeEntity;
import br.com.mtbassi.opovo.api.modules.news_types.repositories.NewsTypeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsTypeService {

    private final NewsTypeRepository repository;
    private final ModelMapper modelMapper;

    public NewsTypeResponse create(NewsTypeRequest data) {
        return modelMapper.map(this.repository.save(buildNewsType(data)), NewsTypeResponse.class);
    }

    public List<NewsTypeResponse> listNewsTypes(){
        List<NewsTypeEntity> newsTypes = this.repository.findByIdJournalist(TokenUtils.getIdToken());
        return convertToNewsTypeResponseList(newsTypes);
    }

    private NewsTypeEntity buildNewsType(NewsTypeRequest data) {
        var newsType = modelMapper.map(data, NewsTypeEntity.class);
        newsType.setIdJournalist(TokenUtils.getIdToken());
        return newsType;
    }

    private List<NewsTypeResponse> convertToNewsTypeResponseList(List<NewsTypeEntity> list){
        return list.stream()
                .map(i -> modelMapper.map(i, NewsTypeResponse.class))
                .toList();
    }
}