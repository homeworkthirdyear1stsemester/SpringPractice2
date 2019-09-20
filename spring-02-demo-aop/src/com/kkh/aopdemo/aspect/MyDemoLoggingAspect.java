package com.kkh.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
    // this is where we add all of our related advices for logging

    // let's start with an @Before advice

    // @Before("execution(public void updateAccount())")

    // 특정 class의 함수에만 적용
    // @Before("execution(public void add*())")

    // 모든 return type들 중 add로 시갖하는 함수에 앞에 붙는 코드들이다
    // @Before("execution(* add*(..))")

    // any method in any class in aopdemo dao package
    @Before("execution(* com.kkh.aopdemo.dao.*.*(..))")
    public void beforeAddAccountAdvice() {
        System.out.println("\n============>>> Executing @Before advice on method");
    }
}
