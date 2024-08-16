package kr.co.groupworks.common.exception.custom;

public class MissingFileException extends RuntimeException{
    public MissingFileException(String message) {
        super(message);
    }
}
