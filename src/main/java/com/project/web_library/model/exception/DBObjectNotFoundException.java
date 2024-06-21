package com.project.web_library.model.exception;

public class DBObjectNotFoundException extends RuntimeException{
    public DBObjectNotFoundException() {
    }

    public DBObjectNotFoundException(String message) {
        super(message);
    }
}
