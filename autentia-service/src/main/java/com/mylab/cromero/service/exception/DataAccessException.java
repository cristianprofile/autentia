package com.mylab.cromero.service.exception;

public class DataAccessException extends ServiceException {

    private static final long serialVersionUID = 3490164901440350602L;

    public DataAccessException() {
    }

    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(Throwable cause) {
        super(cause);
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
