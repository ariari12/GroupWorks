package kr.co.groupworks.common.exception.exhandler;

public class MissingFileException extends RuntimeException{
    public MissingFileException(String message) {
        super(message);
    }
}
