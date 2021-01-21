package io.github.zhuravlyov.redisrest.app.exception;

public class InternalServerException extends RuntimeException {
    public InternalServerException(final String message) {
        super(message);
    }
}
