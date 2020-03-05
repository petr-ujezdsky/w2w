package com.foo.interceptor;

import org.jboss.ejb.client.EJBClientInterceptor;
import org.jboss.ejb.client.EJBClientInvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Client interceptor that logs before and after.
 */
public class AroundLoggingClientInterceptor implements EJBClientInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AroundLoggingClientInterceptor.class);
    
    private static AtomicInteger instanceCounter = new AtomicInteger(0);

    public AroundLoggingClientInterceptor() {
        logger.info("Creating {}", getClass().getSimpleName());
        instanceCounter.incrementAndGet();
    }

    @Override
    public void handleInvocation(EJBClientInvocationContext ejbClientInvocationContext) throws Exception {
        log("handleInvocation - before sendRequest");
        try {
            ejbClientInvocationContext.sendRequest();
            log("handleInvocation - after sendRequest");
        } catch (Exception e) {
            log("handleInvocation - after sendRequest (exception - " + e.getMessage() + ")");
            throw e;
        }
    }

    @Override
    public Object handleInvocationResult(EJBClientInvocationContext ejbClientInvocationContext) throws Exception {
        log("handleInvocationResult - before getResult");
        try {
            Object result = ejbClientInvocationContext.getResult();
            log("handleInvocationResult - after getResult");
            return result;
        } catch (Exception e) {
            log("handleInvocationResult - after getResult (exception - " + e.getMessage() + ")");
            throw e;
        }
    }

    private void log(String msg) {
        logger.info("#{} {}", instanceCounter.get(), msg);
    }
}
