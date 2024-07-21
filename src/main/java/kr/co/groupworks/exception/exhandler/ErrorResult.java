package kr.co.groupworks.exception.exhandler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ErrorResult {
    private String code;
    private String message;
    private Map<String, String> fieldErrors;

    public ErrorResult(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
