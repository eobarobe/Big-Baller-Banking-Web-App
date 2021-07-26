package com.revature.bigballerbank.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.Arrays;


@Aspect
@Component
public class LoggingAspect {
    /**
     * Logging messages based on returns,starts and exceptions is a concern
     * that is across multiple classes within this program. Cross-cutting concern.
     * And so we have annotated this logging class with the Spring @Aspect annotation
     **/

    //creating log4j object
    Logger logger = LogManager.getLogger();

    //A point cut is a predicate that matches join points.
    //Advices are associate with a pointcut expression and will run at any
    //join points matched by the pointcut. ie, execution of a method with a certain name
    @Pointcut("within(com.revature.bigballerbank..*) && !within(com.revature.bigballerbank.filters..*)")
    public void logAll(){
        //pointcuts need empty logic w/in their methods
    }
    //@Before: type of Spring AOP advice that runs before a join point
    //doesn't have the ability to prevent the execution flow preceding to the jp unless an error is thrown
    @Before("logAll()")
    public void logStartMethod(JoinPoint joinPoint){
        String methodSig = extractMethodSignature(joinPoint);
        String argStr = Arrays.toString(joinPoint.getArgs());
        logger.info("{} invoked at {}", methodSig, LocalDateTime.now());
        logger.info("Input arguments: {}",argStr);
    }
    //runs after a join point method completes normally w/o an exception
    @AfterReturning(pointcut = "logAll()",returning = "returnedObj")
    public void logReturnMethod(JoinPoint joinPoint,Object returnedObj){
        String methodSig = extractMethodSignature(joinPoint);
        logger.info("{} successfully returned at {}",methodSig,LocalDateTime.now());
    }
    //executes if a method exits with an exception
    @AfterThrowing(pointcut = "logAll()",throwing = "e")
    public void logMethodException(JoinPoint joinPoint, Throwable e){
        String methodSig = extractMethodSignature(joinPoint);
        logger.warn("{} was thrown in method {} at {} with message: {}",e.getClass().getSimpleName(),methodSig,LocalDateTime.now(),e.getMessage());

    }

    //returns a string version of the method signature
    private String extractMethodSignature(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().toString() + "." + joinPoint.getSignature().getName();
    }
}
