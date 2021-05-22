package ru.geekbrains.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLogger {

    private static final Logger logger = LoggerFactory.getLogger(AspectLogger.class);

    //Фиктивный метод среза (point cut)
    @Pointcut("execution(* ru.geekbrains.controller..*.*(..))")
    private void getPointCut() {
    }

    @Before("getPointCut()")
    public void logBefore(JoinPoint joinPoint){
        logger.info("Log aspect in E-shop triggered with joinPoint (before): {}", joinPoint.toString());
    }

    @After("getPointCut()")
    public void logAfter(JoinPoint joinPoint){
        logger.info("Log aspect triggered in E-shop with joinPoint (after): {}", joinPoint.toString());
    }

}
