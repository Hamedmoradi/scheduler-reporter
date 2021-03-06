package com.pooyabyte.training.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.List;


@ControllerAdvice
//@Slf4j
public class RestExceptionHandler {

    private  static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(value = CustomerRegisteredPreviouslyException.class)
    public ResponseEntity<Object> handleDuplicateCustomerException(CustomerRegisteredPreviouslyException exception) {
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>("customer checked that duplicate national Code does not register again", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = NotificationTypeNotSelectedException.class)
    public ResponseEntity<Object> handleSelectNotificationException(NotificationTypeNotSelectedException exception) {
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>("any check box not selected....", HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = {PooyabyteBaseBusinessException.class})
    public ResponseEntity<List<ExceptionResponse>> handleBusinessEx(PooyabyteBaseBusinessException ex) {
        logger.error(ex.getMessage(), ex);
        return ResponseEntity.status(Integer.parseInt(ex.getMessage()))
                             .body(Arrays.asList(new ExceptionResponse(ex.getMessage())));
    }

    @ExceptionHandler(value = {Throwable.class})
    public ResponseEntity<List<ExceptionResponse>> handleError(Throwable ex) {
        logger.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(Arrays.asList(new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage())));
    }

}
