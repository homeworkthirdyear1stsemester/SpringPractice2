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

    // create point cut for getter methods
    @Pointcut("execution(* com.kkh.aopdemo.dao.*.get*(..))")
    private void getter() {
    }

    // create point cut for setter methods
    @Pointcut("execution(* com.kkh.aopdemo.dao.*.set*(..))")
    private void setter() {
    }

    // create pointcut : include package ... exclude getter/setter
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    private void forDaoPackageNoGetterSetter() {
    }

    // only for Pointcut method name to advice
    @Before("forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n============>>> Executing @Before advice on method");
    }

    @Before("forDaoPackageNoGetterSetter()") // resusing forDaoPackage method
    public void performApiAnalytics() {
        System.out.println("\n============>>> Performing API analytics");
    }

    @Before("forDaoPackageNoGetterSetter()")
    public void logToCloudAsync() {
        System.out.println("\n============>>> Logging to Cloud in async fashion");
    }
}
