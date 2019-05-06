package com.occ.code.exception;

public class InvalidFilePathException extends RuntimeException {
    public InvalidFilePathException(String errorMessage) {
        super(errorMessage);
    }
}
