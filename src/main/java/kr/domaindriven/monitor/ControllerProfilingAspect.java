package kr.domaindriven.monitor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Created by donghoon on 2016. 8. 24..
 */
@Aspect
@Component
public class ControllerProfilingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StopWatch stopWatch;

    @Pointcut("execution(public * kr.domaindriven.web..*(..))")
    private void profileTarget() {
    }

    @Around("profileTarget()")
    public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
        String signatureStr = joinPoint.getSignature().toShortString();
        String displayName = joinPoint.getSignature().getName();
        Object[] params = joinPoint.getArgs();
        logger.debug(signatureStr + ": 시작");
        stopWatch.start();
        try {
            Object result = joinPoint.proceed();
            for (Object obj : params) {
                logger.info(displayName + " param: " + obj);
            }
            return result;
        } finally {
            stopWatch.stop();
            logger.debug(signatureStr + ": 종료");
            logger.info(signatureStr + ": 실행시간=" + stopWatch.getTotalTimeMillis() + "ms");
        }

    }

    @Bean
    public StopWatch stopWatch() {
        return new StopWatch();
    }

}
