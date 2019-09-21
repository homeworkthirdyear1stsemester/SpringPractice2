package com.kkh.aopdemo.aspect;

import com.kkh.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Around("execution(* com.kkh.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // print out method we are advising on
        String method = proceedingJoinPoint.getSignature().toShortString();
        logger.info("\n=======>>> Executing @Around on method : " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // now, let's execute the method
        Object result = proceedingJoinPoint.proceed();

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and display it
        long duration = end - begin;
        logger.info("\n=======> Duration : " + duration / 1000.0 + " seconds");

        return result;
    }

    @After("execution(* com.kkh.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {
        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        logger.info("\n=======>>> Executing @After (finally) on method : " + method);
    }

    @AfterThrowing(
            pointcut = "execution(* com.kkh.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "exc"
    )
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable exc) {
        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        logger.info("\n=======>>> Executing @AfterThrowing on method : " + method);

        // log the exception
        logger.info("\n=======>>> The Exception is : " + exc);
    }

    // add a new advice for @AfterReturning on the findAccounts method
    @AfterReturning(
            pointcut = "execution(* com.kkh.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(
            JoinPoint joinPoint, List<Account> result // returning 이랑 동일한 이름이어야 한다
    ) {
        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        logger.info("\n=======>>> Executing @AfterReturning on method : " + method);

        // print out the results of the method call
        logger.info("\n=======>>> result is : " + result);

        // let's post-process the data ... let's modify it!

        // convert the account names to uppercase
        this.convertAccountNamesToUpperCase(result);

        logger.info("\n=======>>> result is : " + result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        // loop through accounts
        for (Account account : result) {
            // get uppercase version of name
            String upperName = account.getName().toUpperCase();

            // update the name on the account
            account.setName(upperName);
        }
    }

    @Before("com.kkh.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        logger.info("\n============>>> Executing @Before advice on method");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        logger.info("Method : " + methodSignature); // check output of MethodSignature

        // display method arguemtns

        // get args
        Object[] args = joinPoint.getArgs();

        // loop thru args
        for (Object arg : args) {
            logger.info(arg.toString());

            if (arg instanceof Account) {
                // downcast and print Account specific stuff
                Account account = (Account) arg;

                logger.info("account name : " + account.getName());
                logger.info("account level : " + account.getLevel());
            }
        }
    }
}
