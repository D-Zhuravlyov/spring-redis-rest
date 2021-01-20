package io.github.zhuravlyov.redisrest.app.exception;

public class InternalServerException extends RuntimeException {
    public InternalServerException(final String message) {
        super(message);
    }

    public InternalServerException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
