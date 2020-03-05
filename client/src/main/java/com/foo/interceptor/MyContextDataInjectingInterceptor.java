package com.foo.interceptor;

import com.foo.MyContextData;
import org.jboss.ejb.client.EJBClientInterceptor;
import org.jboss.ejb.client.EJBClientInvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static com.foo.interceptor.MyContextDataRetrievingServerInterceptor.CONTEXT_DATA_ATTRIBUTE_NAME;

/**
 * Client interceptor that injects {@link MyContextData} into EJB metadata.
 */
public class MyContextDataInjectingInterceptor implements EJBClientInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(MyContextDataInjectingInterceptor.class);

    @Override
    public void handleInvocation(EJBClientInvocationContext ejbClientInvocationContext) throws Exception {
        UUID requestId = UUID.randomUUID();
        MyContextData contextData = new MyContextData(requestId);
        ejbClientInvocationContext.getContextData().put(CONTEXT_DATA_ATTRIBUTE_NAME, contextData);
        logger.info("Generated requestId {}", requestId);

        ejbClientInvocationContext.sendRequest();
    }

    @Override
    public Object handleInvocationResult(EJBClientInvocationContext ejbClientInvocationContext) throws Exception {
        return ejbClientInvocationContext.getResult();
    }
}
