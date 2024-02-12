package br.com.mtbassi.opovo.api.modules.news_types.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NewsTypeNotFoundException extends RuntimeException {

    public NewsTypeNotFoundException(){
        super("News type not found.");
    }
}