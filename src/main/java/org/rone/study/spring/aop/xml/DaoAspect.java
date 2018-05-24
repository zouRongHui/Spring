package org.rone.study.spring.aop.xml;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;

public class DaoAspect {
	
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		Object [] args = joinPoint.getArgs();
		
		System.out.println("The method " + methodName + " begins with " + Arrays.asList(args));
	}
	
	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		
		System.out.println("The method " + methodName + " ends.");
	}
	
	public void afterReturnning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		
		System.out.println("The method " + methodName + " return " + result);
	}

}
