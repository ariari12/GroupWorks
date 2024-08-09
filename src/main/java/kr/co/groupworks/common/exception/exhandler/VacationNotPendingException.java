package kr.co.groupworks.common.exception.exhandler;

public class VacationNotPendingException extends RuntimeException {
    public VacationNotPendingException(String message) {
        super(message);
    }
}
