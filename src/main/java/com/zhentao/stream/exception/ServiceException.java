package com.zhentao.stream.exception;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -7988658765213327725L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
