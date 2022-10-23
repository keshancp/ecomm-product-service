package com.ecomm.productservice.aop;



import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.ecomm.productservice.util.CommonUtil;


@Aspect
@Component
public class LoggingAspect {

	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *) || within(com.ecomm.productservice.controller..*)")
	public void restLayerPointcut() {
		/**
		 * restLayerPointcut
		 */
	}

	@Pointcut("within(@org.springframework.stereotype.Service *) || within(com.ecomm.productservice.service.impl..*)")
	public void serviceLayerPointcut() {
		/**
		 * serviceLayerPointcut
		 */
	}

	@Pointcut("within(@org.springframework.stereotype.Repository *)")
	public void repositoryLayerPointcut() {
		/**
		 * repositoryLayerPointcut
		 */
	}

	

	@Around("restLayerPointcut()")
	public Object logAroundRestLayer(ProceedingJoinPoint joinPoint) throws Throwable {
		return logAround(joinPoint, "REST_LAYER");
	}

	@Around("serviceLayerPointcut()")
	public Object logAroundServiceLayer(ProceedingJoinPoint joinPoint) throws Throwable {
		return logAround(joinPoint, "SERVICE_LAYER");
	}

	@Around("repositoryLayerPointcut()")
	public Object logAroundRepositoryLayer(ProceedingJoinPoint joinPoint) throws Throwable {
		return logAround(joinPoint, "REPOSITORY_LAYER");
	}


	@AfterThrowing(pointcut = "restLayerPointcut()", throwing = "e")
	public void logAfterThrowingRestLayer(JoinPoint joinPoint, Throwable e) {
		logAfterThrowing(joinPoint, e, "REST_LAYER");
	}

	@AfterThrowing(pointcut = "serviceLayerPointcut()", throwing = "e")
	public void logAfterThrowingServiceLayer(JoinPoint joinPoint, Throwable e) {
		logAfterThrowing(joinPoint, e, "SERVICE_LAYER");
	}

	@AfterThrowing(pointcut = "repositoryLayerPointcut()", throwing = "e")
	public void logAfterThrowingRepositoryLayer(JoinPoint joinPoint, Throwable e) {
		logAfterThrowing(joinPoint, e, "REPOSITORY_LAYER");
	}

	

	private void logAfterThrowing(JoinPoint joinPoint, Throwable e, String layer) {

		Logger logger = LogManager.getLogger(joinPoint.getTarget().getClass());

		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

		String className = methodSignature.getDeclaringTypeName();
		String methodName = methodSignature.getName();
		Map<String, Object> parameters = getParameters(joinPoint);
		String requestId = (String) parameters.get("traceId");

		logger.error("ERROR [{}] [TraceId={}] {}.{}:: cause={}", layer, requestId, className, methodName,
				e.getMessage());
	}

	private Object logAround(ProceedingJoinPoint joinPoint, String layer) throws Throwable {

		Logger logger = LogManager.getLogger(joinPoint.getTarget().getClass());

		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

		String className = methodSignature.getDeclaringTypeName();
		String methodName = methodSignature.getName();
		Map<String, Object> parameters = getParameters(joinPoint);
		String requestId = (String) parameters.get("traceId");
		if (logger.isInfoEnabled()) {
			logger.info("START [{}] [TraceId={}] {}.{}:: arguments={}", layer, requestId, className,
					methodName, CommonUtil.convertToString(parameters));
		}
		final StopWatch stopWatch = new StopWatch();

		stopWatch.start();
		Object result = joinPoint.proceed();
		stopWatch.stop();
		if (logger.isInfoEnabled()) {
			logger.info("END [{}] [TraceId={}] {}.{}:: timeTaken={} ms|result={}", layer, requestId,
					className, methodName, stopWatch.getTotalTimeMillis(), CommonUtil.convertToString(result));
		}
		return result;
	}

	private Map<String, Object> getParameters(JoinPoint joinPoint) {
		CodeSignature signature = (CodeSignature) joinPoint.getSignature();

		HashMap<String, Object> map = new HashMap<>();

		String[] parameterNames = signature.getParameterNames();

		for (int i = 0; i < parameterNames.length; i++) {
			map.put(parameterNames[i], joinPoint.getArgs()[i]);
		}

		return map;
	}
}
