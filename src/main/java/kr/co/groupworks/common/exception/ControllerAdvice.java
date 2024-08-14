package kr.co.groupworks.common.exception;

import jakarta.persistence.EntityNotFoundException;
import kr.co.groupworks.common.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResult> handleRuntimeException(RuntimeException ex) {
        log.error("[RuntimeException] ex", ex);
        ErrorResult errorResult = new ErrorResult("RuntimeException", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResult> handleIllegalStateException(IllegalStateException ex) {
        log.error("[handleIllegalStateException] ex", ex);
        ErrorResult errorResult = new ErrorResult("ILLEGAL_STATE_EXCEPTION", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResult> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("[handleIllegalArgumentException] ex", ex);
        ErrorResult errorResult = new ErrorResult("ILLEGAL_ARGUMENT_ERROR", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResult> handleEntityNotFoundException(EntityNotFoundException ex) {
        log.error("[EntityNotFoundException] ex", ex);
        ErrorResult errorResult = new ErrorResult("ENTITY_NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
    }
    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<ErrorResult> handleEntityNotFoundException(MissingServletRequestPartException ex) {
        log.error("[MissingServletRequestPartException] ex", ex);
        ErrorResult errorResult = new ErrorResult(ex.getTitleMessageCode(), "파일 첨부는 필수 입니다.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    // 필드 에러
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("[handleValidationExceptions] ex", ex);

        // 필드 오류 수집
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ErrorResult errorResult = new ErrorResult("VALIDATION_ERROR", "잘못된 입력입니다. 입력 값을 확인해주세요.", fieldErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }
}
