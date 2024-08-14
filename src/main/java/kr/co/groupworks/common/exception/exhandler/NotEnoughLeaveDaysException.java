package kr.co.groupworks.common.exception.exhandler;

public class NotEnoughLeaveDaysException extends RuntimeException {
    public NotEnoughLeaveDaysException(String message) {
        super(message);
    }
}
