package br.com.mtbassi.opovo.api.modules.news_types.exceptions;

public class NewsTypeNotFoundException extends RuntimeException {

    public NewsTypeNotFoundException(){
        super("News type not found.");
    }
}