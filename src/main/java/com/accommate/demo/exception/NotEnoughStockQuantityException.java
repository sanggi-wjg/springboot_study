package com.accommate.demo.exception;

public class NotEnoughStockQuantityException extends RuntimeException {

    public NotEnoughStockQuantityException() {
        super();
    }

    public NotEnoughStockQuantityException(String message) {
        super(message);
    }

    public NotEnoughStockQuantityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughStockQuantityException(Throwable cause) {
        super(cause);
    }

    public NotEnoughStockQuantityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
