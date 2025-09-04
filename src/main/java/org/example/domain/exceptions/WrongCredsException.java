package org.example.domain.exceptions;

public class WrongCredsException extends RuntimeException {
    public WrongCredsException(String message) {
        super(message);
    }
}
