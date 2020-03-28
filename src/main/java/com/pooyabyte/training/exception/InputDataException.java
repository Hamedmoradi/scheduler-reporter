package com.pooyabyte.training.exception;

public class InputDataException extends PooyabyteBaseBusinessException {

    public InputDataException() {
        super("There was an error entering the data....");
    }

    public InputDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
