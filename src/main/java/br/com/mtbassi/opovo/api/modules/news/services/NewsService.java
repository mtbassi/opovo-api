package br.com.mtbassi.opovo.api.modules.news.services;

import br.com.mtbassi.opovo.api.modules.commons.utils.TokenUtils;
import br.com.mtbassi.opovo.api.modules.news.dto.NewsRequest;
import br.com.mtbassi.opovo.api.modules.news.dto.NewsResponse;
import br.com.mtbassi.opovo.api.modules.news.entities.NewsEntity;
import br.com.mtbassi.opovo.api.modules.news.repositories.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository repository;
    private final ModelMapper modelMapper;

    public NewsResponse create(NewsRequest data) {
        return modelMapper.map(this.repository.save(buildNews(data)), NewsResponse.class);
    }

    public boolean hasNewsWithTypeOfNews(UUID id) {
        return !this.repository.findByIdNewsType(id).isEmpty();
    }

    private NewsEntity buildNews(NewsRequest data) {
        var news = modelMapper.map(data, NewsEntity.class);
        news.setIdJournalist(TokenUtils.getIdToken());
        return news;
    }
}