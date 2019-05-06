package com.occ.code.exception;

public class EmptyFileException extends RuntimeException {
    public EmptyFileException(String errorMessage) {
        super(errorMessage);
    }
}