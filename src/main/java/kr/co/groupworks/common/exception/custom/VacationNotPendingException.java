package kr.co.groupworks.common.exception.custom;

public class VacationNotPendingException extends RuntimeException {
    public VacationNotPendingException(String message) {
        super(message);
    }
}
