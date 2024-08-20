package kr.co.groupworks.common.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class PerformanceAspect {

    @Around("execution(* kr.co.groupworks..*(..))")
    public Object logExecutionTimeByDomainAndLayer(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecutionTime(joinPoint);
    }

    // 공통 성능 측정 로직
    private Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;
        log.info("{} executed in {} ms", joinPoint.getSignature(), executionTime);

        return proceed;
    }
}