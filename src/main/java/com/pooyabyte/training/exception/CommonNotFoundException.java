package com.pooyabyte.training.exception;


public class CommonNotFoundException extends PooyabyteBaseBusinessException {

    public CommonNotFoundException() {
        super("Resource not found");
    }

    public CommonNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
