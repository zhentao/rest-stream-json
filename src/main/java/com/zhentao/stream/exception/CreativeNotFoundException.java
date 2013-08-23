package com.zhentao.stream.exception;

public class CreativeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 6192206752330975555L;

    public CreativeNotFoundException(long id) {
        super("Creative is not found with id: " + id);
    }
}
