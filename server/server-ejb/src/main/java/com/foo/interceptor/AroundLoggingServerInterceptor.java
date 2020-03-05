package com.foo.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * Server interceptor that logs before and after.
 */
public class AroundLoggingServerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(AroundLoggingServerInterceptor.class);

    @AroundInvoke
    public Object logBeforeAndAfter(InvocationContext ctx) throws Exception {
        String className = ctx.getTarget().getClass().getSimpleName();
        String methodName = ctx.getMethod().getName();

        try {
            logger.info("{}#{} - start", className, methodName);
            Object result = ctx.proceed();
            logger.info("{}#{} - end", className, methodName);
            return result;
        } catch (Throwable e){
            logger.info("{}#{} - end (exception - {})", className, methodName, e.getMessage());
            throw e;
        }
    }
}
