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
        ejbClientInvocationContext.sendRequest();
        log("handleInvocation - after sendRequest");
    }

    @Override
    public Object handleInvocationResult(EJBClientInvocationContext ejbClientInvocationContext) throws Exception {
        log("handleInvocationResult - before getResult");
        try {
            return ejbClientInvocationContext.getResult();
        } finally {
            log("handleInvocationResult - after getResult");
        }
    }

    private void log(String msg) {
        logger.info("#{} {}", instanceCounter.get(), msg);
    }
}
