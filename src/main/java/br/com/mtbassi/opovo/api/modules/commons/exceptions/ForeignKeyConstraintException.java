package br.com.mtbassi.opovo.api.modules.commons.exceptions;


public class ForeignKeyConstraintException extends RuntimeException {

    public ForeignKeyConstraintException(String message) {
        super(message);
    }
}