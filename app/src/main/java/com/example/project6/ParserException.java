package com.example.project6;

public class ParserException extends Exception {
    public ParserException(String description) {
        super(description);
    }

    public ParserException(String description, Throwable cause) {
        super(description, cause);
    }
}

