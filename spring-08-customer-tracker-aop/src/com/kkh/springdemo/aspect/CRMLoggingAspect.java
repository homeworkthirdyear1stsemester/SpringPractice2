package com.kkh.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {
    // setup logger
    private Logger logger = Logger.getLogger(getClass().getName());

    // setup pointcut declarations
    @Pointcut("execution(* com.kkh.springdemo.controller.*.*(..))")
    private void forControllerPackage() {
    }

    // do the same for service and dao
    @Pointcut("execution(* com.kkh.springdemo.service.*.*(..))")
    private void forServicePackage() {
    }

    @Pointcut("execution(* com.kkh.springdemo.dao.*.*(..))")
    private void forDaoPackage() {
    }


    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {
    }

    // add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        // display method are calling
        String theMethod = joinPoint.getSignature().toShortString();
        this.logger.info("=====>> in @Before : calling method : " + theMethod);

        // display the arguments to the method

        // get the arguments
        Object[] args = joinPoint.getArgs();

        // loop thru and display args
        for (Object arg : args) {
            this.logger.info("=====>> argument : " + arg);
        }
    }

    // add @AfterReturning advice
}
