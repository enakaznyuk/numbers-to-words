package org.example.domain.exception;

public class InvalidNumberException extends RuntimeException{

    public InvalidNumberException(String message) {
        super(message);
    }
}
