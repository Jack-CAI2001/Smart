package com.smart.smartApi.exception;

public class StorageFileExistsException extends RuntimeException {
    public StorageFileExistsException(String message) {
        super(message);
    }
}
