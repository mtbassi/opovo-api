package br.com.mtbassi.opovo.api.modules.news.exceptions;

public class NewsNotFoundException extends RuntimeException {

    public NewsNotFoundException() {
        super("News not found.");
    }
}