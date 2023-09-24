package com.alejo.challenge.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ExternalApiCallException extends Exception {
    public ExternalApiCallException(String message) {
        super(message);
    }
}
