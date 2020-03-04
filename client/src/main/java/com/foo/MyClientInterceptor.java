package com.foo;

import org.jboss.ejb.client.EJBClientInterceptor;
import org.jboss.ejb.client.EJBClientInvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Client interceptor
 */
public class MyClientInterceptor implements EJBClientInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(MyClientInterceptor.class);
    
    private static int counter = 0;

    public MyClientInterceptor() {
        logger.info("Creating MyClientInterceptor");
        counter++;
    }

    @Override
    public void handleInvocation(EJBClientInvocationContext ejbClientInvocationContext) throws Exception {
        logger.info("handleInvocation - before sendRequest, " + counter);
        ejbClientInvocationContext.sendRequest();
        logger.info("handleInvocation - after sendRequest");
    }

    @Override
    public Object handleInvocationResult(EJBClientInvocationContext ejbClientInvocationContext) throws Exception {
        logger.info("handleInvocationResult - before getResult");
        try {
            return ejbClientInvocationContext.getResult();
        } finally {
            logger.info("handleInvocationResult - after getResult");
        }
    }
}
