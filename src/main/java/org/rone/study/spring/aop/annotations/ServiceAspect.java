package org.rone.study.spring.aop.annotations;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*
 * 1. 引入相关jar包
 * 2. 将目标类和切面类都加入IOC中
 * 3. 基于注解的AOP需要在spring配置文件中加入 <aop:aspectj-autoproxy /> 是注解起作用
 * 4. 编写切面类，
 * 5. 配置切面类
 * 	5.1 切面必须是 IOC 中的 bean: 实际添加了 @Component 注解
 * 	5.2 声明是一个切面: 添加 @Aspect
 *  5.3 声明通知: 即额外加入功能对应的方法. 
 *  5.3.1 前置通知: @Before("execution(* com.aop.*.*(..))")
 *  	第一个*：方法的访问控制符和返回类型；
 *  	第二个*：类名；
 *  	第三个*：方法名。
 * 		@Before 表示在目标方法执行之前执行 @Before 标记的方法的方法体. 
 * 		@Before 里面的是切入点表达式: 
 * 6. 在通知中访问连接细节: 可以在通知方法中添加 JoinPoint 类型的参数, 从中可以访问到方法的签名和方法的参数. 
 * 7. @After 表示后置通知: 在方法执行之后执行的代码. 无论是否抛出异常.
 * 8. @AfterReturning 返回通知，需要在注解中表明接受的返回对象名；
 * 9. @AfterThrowing 异常通知，产生异常触发。需要表明异常对象名；
 * 10.@Around 环绕通知，等同于代理者模式。包含其他四中类型的通知。
 * 11.当有多个切面时，使用@Order 来设置优先级，值越小越先执行;
 * 12.通过@Pointcut 注解去定义一个方法(无需实现体)用于切点的重用，在其他通知中用调用改方法；
 */
@Order(0)
@Aspect
@Component
public class ServiceAspect {
	
	@Pointcut("execution(* org.rone.study.spring.aop.annotations.*.*(..))")
	public void demoCut() {}
	
	@Before("demoCut()")
//	@Before("execution(* org.rone.study.spring.aop.annotations.*.*(..))")
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		Object [] args = joinPoint.getArgs();
		
		System.out.println("The method " + methodName + " begins with " + Arrays.asList(args));
	}
	
	@After("demoCut()")
	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		
		System.out.println("The method " + methodName + " ends.");
	}
	
	@AfterReturning(value="demoCut()", returning="result")
	public void afterReturnning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		
		System.out.println("The method " + methodName + " return " + result);
	}
	
	@AfterThrowing(value="demoCut()", throwing="e")
	public void afterThrowing(JoinPoint joinPoint, Exception e) {
		String methodName = joinPoint.getSignature().getName();
		
		System.out.println("The method " + methodName + " throw " + e.getMessage());
	}
	
	@Around("demoCut()")
	public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) {
		Object result = null;
		String methodName = proceedingJoinPoint.getSignature().getName();
		
		try {
			//@Before 前置通知
			System.out.println("The method " + methodName + " around begin.");
			result = proceedingJoinPoint.proceed();
			//@AfterReturning 返回通知
			System.out.println("The method " + methodName + " around return.");
		} catch (Throwable e) {
			//@AfterThrowing 异常通知
			System.out.println("The method " + methodName + " around throw.");
		}
		//@After 后置通知
		System.out.println("The method " + methodName + " around ends.");
		
		return result;
	}

}
