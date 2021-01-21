package io.github.zhuravlyov.redisrest.app.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import static org.springframework.core.annotation.AnnotatedElementUtils.findMergedAnnotation;

@Log4j2
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatus status,
                                                                  final WebRequest request) {
        final StringBuilder message = buildValidationMessage(ex);
        final ApiError error = new ApiError(message.toString());
        return super.handleExceptionInternal(ex, error, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiError> handleEverything(Throwable th) {
        log.error(th.getMessage(), th);
        final ApiError error = new ApiError(th);
        return ResponseEntity.status(resolveResponseStatus(th)).body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolation(ConstraintViolationException ex) {
        final StringBuilder message = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
            message.append(constraintViolation);
        }
        log.error(message.toString(), ex);
        final ApiError error = new ApiError(message.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    private StringBuilder buildValidationMessage(final MethodArgumentNotValidException ex) {
        final StringBuilder message = new StringBuilder();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            message.append(" Argument Not Valid: ");
            message.append(error.getField());
            message.append(".  Restricted value: ");
            message.append(error.getRejectedValue());
        }
        return message;
    }

    private HttpStatus resolveResponseStatus(Throwable throwable) {
        final ResponseStatus annotation = findMergedAnnotation(throwable.getClass(), ResponseStatus.class);
        return (annotation != null) ? annotation.value() : HttpStatus.INTERNAL_SERVER_ERROR;
    }
}