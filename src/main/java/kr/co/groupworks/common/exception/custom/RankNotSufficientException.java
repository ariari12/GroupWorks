package kr.co.groupworks.common.exception.custom;

public class RankNotSufficientException extends RuntimeException {
    public RankNotSufficientException(String message) {
        super(message);
    }
}