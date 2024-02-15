package br.com.mtbassi.opovo.api.modules.news_types.services;

import br.com.mtbassi.opovo.api.modules.commons.exceptions.ForeignKeyConstraintException;
import br.com.mtbassi.opovo.api.modules.commons.utils.TokenUtils;
import br.com.mtbassi.opovo.api.modules.news.services.NewsService;
import br.com.mtbassi.opovo.api.modules.news_types.dto.NewsTypeRequest;
import br.com.mtbassi.opovo.api.modules.news_types.dto.NewsTypeResponse;
import br.com.mtbassi.opovo.api.modules.news_types.entities.NewsTypeEntity;
import br.com.mtbassi.opovo.api.modules.news_types.exceptions.NewsTypeNotFoundException;
import br.com.mtbassi.opovo.api.modules.news_types.repositories.NewsTypeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NewsTypeService {

    private final NewsTypeRepository repository;
    private final ModelMapper modelMapper;
    private final NewsService newsService;

    public NewsTypeResponse create(NewsTypeRequest data) {
        return modelMapper.map(this.repository.save(buildNewsType(data)), NewsTypeResponse.class);
    }

    public List<NewsTypeResponse> listNewsTypes(){
        List<NewsTypeEntity> newsTypes = this.repository.findByIdJournalist(TokenUtils.getIdToken());
        return convertToNewsTypeResponseList(newsTypes);
    }

    public NewsTypeResponse update(NewsTypeRequest data, UUID id){
        this.repository.findById(id).orElseThrow(NewsTypeNotFoundException::new);
        var newsType = NewsTypeEntity.builder()
                .id(id)
                .idJournalist(TokenUtils.getIdToken())
                .name(data.getName())
                .build();
        return modelMapper.map(this.repository.save(newsType), NewsTypeResponse.class);
    }

    public void delete(UUID id){
        if(this.newsService.hasNewsWithTypeOfNews(id)) throw new ForeignKeyConstraintException("Type of news linked to existing news.");
        var newsType = this.repository.findById(id).orElseThrow(NewsTypeNotFoundException::new);
        this.repository.delete(newsType);
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