package com.pooyabyte.training.exception;

public class CustomerRegisteredPreviouslyException extends PooyabyteBaseBusinessException {
    private static final long serialVersionUID = 1L;

    public CustomerRegisteredPreviouslyException(String message) {
        super(message);
    }

    public CustomerRegisteredPreviouslyException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerRegisteredPreviouslyException() {

    }
}
