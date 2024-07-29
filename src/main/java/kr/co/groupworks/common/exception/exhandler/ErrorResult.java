package kr.co.groupworks.common.exception.exhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResult {
    private String code;
    private String message;
    private Map<String, String> fieldErrors;

    public ErrorResult(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
