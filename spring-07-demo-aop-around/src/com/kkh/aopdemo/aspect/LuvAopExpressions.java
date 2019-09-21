package com.kkh.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LuvAopExpressions {

    @Pointcut("execution(* com.kkh.aopdemo.dao.*.*(..))")
    public void forDaoPackage() {
    }

    // create point cut for getter methods
    @Pointcut("execution(* com.kkh.aopdemo.dao.*.get*(..))")
    public void getter() {
    }

    // create point cut for setter methods
    @Pointcut("execution(* com.kkh.aopdemo.dao.*.set*(..))")
    public void setter() {
    }

    // create pointcut : include package ... exclude getter/setter
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter() {
    }
}
