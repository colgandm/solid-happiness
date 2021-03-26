package com.intercom.takehome.backend.exception;

public class FileRetrievalException extends RuntimeException {
    public FileRetrievalException(Exception ex) {
        super(ex);
    }

    public FileRetrievalException(String message, Exception ex) {
        super(message, ex);
    }

    public static FileRetrievalException createFileRetrieverException(String message, Exception exception) {
        return new FileRetrievalException(message, exception);
    }
}
