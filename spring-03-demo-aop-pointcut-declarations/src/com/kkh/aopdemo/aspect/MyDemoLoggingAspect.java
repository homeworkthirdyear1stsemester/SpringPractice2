package com.kkh.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    @Pointcut("execution(* com.kkh.aopdemo.dao.*.*(..))")
    private void forDaoPackage() {

    }

    // only for Pointcut method name to advice
    @Before("forDaoPackage()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n============>>> Executing @Before advice on method");
    }
}
