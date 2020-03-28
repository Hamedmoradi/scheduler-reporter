package com.pooyabyte.training.exception;

public class PooyabyteBaseBusinessException extends RuntimeException {

    public PooyabyteBaseBusinessException() {
        super();
    }

    public PooyabyteBaseBusinessException(String message) {
        super(message);
    }

    public PooyabyteBaseBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
