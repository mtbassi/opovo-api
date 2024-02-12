package br.com.mtbassi.opovo.api.modules.commons.exceptions;

public class ModelException extends RuntimeException {

    public ModelException(String message) {
        super(message);
    }

    public ModelException(String message, Throwable e) {
        super(message, e);
    }
}