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
        String packageName = joinPoint.getSignature().getDeclaringTypeName();

        String[] packageParts = packageName.split("\\.");
        String domain = packageParts[3];  // 도메인 추출 (예: calendar, notification 등)
        String layer = packageParts[4];   // 계층 추출 (예: controller, service, repository)

        return logExecutionTime(joinPoint, domain, layer);
    }

    // 공통 성능 측정 로직
    private Object logExecutionTime(ProceedingJoinPoint joinPoint, String domain, String layer) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;
        log.info("[{} Domain - {} Layer] {} executed in {} ms", domain, layer, joinPoint.getSignature(), executionTime);

        return proceed;
    }
}
