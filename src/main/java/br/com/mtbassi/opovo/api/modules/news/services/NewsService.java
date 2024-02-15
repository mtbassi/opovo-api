package br.com.mtbassi.opovo.api.modules.news.services;

import br.com.mtbassi.opovo.api.modules.commons.utils.TokenUtils;
import br.com.mtbassi.opovo.api.modules.news.dto.NewsRequest;
import br.com.mtbassi.opovo.api.modules.news.dto.NewsResponse;
import br.com.mtbassi.opovo.api.modules.news.entities.NewsEntity;
import br.com.mtbassi.opovo.api.modules.news.exceptions.NewsNotFoundException;
import br.com.mtbassi.opovo.api.modules.news.repositories.NewsRepository;
import br.com.mtbassi.opovo.api.modules.news_types.dto.NewsTypeResponse;
import br.com.mtbassi.opovo.api.modules.news_types.entities.NewsTypeEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Condition;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository repository;
    private final ModelMapper modelMapper;

    public NewsResponse create(NewsRequest data) {
        return modelMapper.map(this.repository.save(buildNews(data)), NewsResponse.class);
    }

    public List<NewsResponse> listNews() {
        List<NewsEntity> news = this.repository.findByIdJournalist(TokenUtils.getIdToken());
        return convertToNewsResponseList(news);
    }

    public List<NewsResponse> listNewsByType(UUID typeId) {
        return convertToNewsResponseList(this.repository.findByIdNewsType(typeId));
    }

    public NewsResponse update(NewsRequest data, UUID id){
        var news = this.repository.findById(id).orElseThrow(NewsNotFoundException::new);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(data, news);
        return modelMapper.map(this.repository.save(news), NewsResponse.class);
    }

    public void delete(UUID id){
        var news = this.repository.findById(id).orElseThrow(NewsNotFoundException::new);
        this.repository.delete(news);
    }

    public boolean hasNewsWithTypeOfNews(UUID id) {
        return !this.repository.findByIdNewsType(id).isEmpty();
    }

    private NewsEntity buildNews(NewsRequest data) {
        var news = modelMapper.map(data, NewsEntity.class);
        news.setIdJournalist(TokenUtils.getIdToken());
        return news;
    }

    private List<NewsResponse> convertToNewsResponseList(List<NewsEntity> list){
        return list.stream()
                .map(i -> modelMapper.map(i, NewsResponse.class))
                .toList();
    }
}