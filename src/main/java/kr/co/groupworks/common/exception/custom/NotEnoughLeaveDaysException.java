package kr.co.groupworks.common.exception.custom;

public class NotEnoughLeaveDaysException extends RuntimeException {
    public NotEnoughLeaveDaysException(String message) {
        super(message);
    }
}
