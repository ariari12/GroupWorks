package kr.co.groupworks.common.exception;


import kr.co.groupworks.common.exception.exhandler.ErrorResult;
import kr.co.groupworks.common.exception.exhandler.NotEnoughLeaveDaysException;
import kr.co.groupworks.common.exception.exhandler.VacationNotPendingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestControllerAdvice
public class VacationControllerAdvice {
    @ExceptionHandler(VacationNotPendingException.class)
    public ModelAndView handleVacationNotPendingException(VacationNotPendingException ex) {
        ModelAndView modelAndView = new ModelAndView("error/401");
        modelAndView.setStatus(HttpStatus.UNAUTHORIZED);
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(NotEnoughLeaveDaysException.class)
    public ResponseEntity<ErrorResult> handleNotEnoughLeaveDaysException(NotEnoughLeaveDaysException ex) {
        log.error("[handleNotEnoughLeaveDaysException] ex", ex);
        ErrorResult errorResult = new ErrorResult("notEnough_leaveDays_exception", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }
}
