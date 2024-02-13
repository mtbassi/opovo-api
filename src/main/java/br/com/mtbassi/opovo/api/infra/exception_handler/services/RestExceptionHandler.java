package br.com.mtbassi.opovo.api.infra.exception_handler.services;

import br.com.mtbassi.opovo.api.infra.exception_handler.dto.RestErrorMessage;
import br.com.mtbassi.opovo.api.modules.commons.exceptions.ForeignKeyConstraintException;
import br.com.mtbassi.opovo.api.modules.commons.exceptions.ModelException;
import br.com.mtbassi.opovo.api.modules.news.exceptions.NewsNotFoundException;
import br.com.mtbassi.opovo.api.modules.news_types.exceptions.NewsTypeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ModelException.class)
    private ResponseEntity<RestErrorMessage> modelHandler (ModelException e){
        HttpStatus httpStatus = this.resolveAnnotatedResponseStatus(e);
        return ResponseEntity.status(httpStatus)
                .body(buildRestErrorMessage(e, httpStatus));
    }

    @ExceptionHandler({NewsTypeNotFoundException.class, NewsNotFoundException.class})
    private ResponseEntity<RestErrorMessage> newsTypeNotFoundHandler(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(buildRestErrorMessage(e, HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(ForeignKeyConstraintException.class)
    private ResponseEntity<RestErrorMessage> foreignKeyConstraintHandler(ForeignKeyConstraintException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(buildRestErrorMessage(e, HttpStatus.CONFLICT));
    }

    private RestErrorMessage buildRestErrorMessage(Exception e, HttpStatus httpStatus){
        return RestErrorMessage.builder()
                .httpStatus(httpStatus.value())
                .message(e.getMessage())
                .build();
    }

    private HttpStatus resolveAnnotatedResponseStatus(Exception e) {
        ResponseStatus annotation = e.getClass().getAnnotation(ResponseStatus.class);
        return (annotation != null) ? annotation.value() : HttpStatus.INTERNAL_SERVER_ERROR;
    }
}