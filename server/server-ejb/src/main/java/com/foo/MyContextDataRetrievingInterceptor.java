package com.foo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * Server interceptor that retrieves {@link MyContextData} from EJB metadata.
 */
public class MyContextDataRetrievingInterceptor {

    private final Logger logger = LoggerFactory.getLogger(MyContextDataRetrievingInterceptor.class);

    public static final String CONTEXT_DATA_ATTRIBUTE_NAME = MyContextDataRetrievingInterceptor.class.getSimpleName() + ".CONTEXT_DATA";

    @AroundInvoke
    public Object extractContextData(InvocationContext ctx) throws Exception {
        MyContextData contextData = (MyContextData) ctx.getContextData().get(CONTEXT_DATA_ATTRIBUTE_NAME);

        logger.info("RequestId {}", contextData.getRequestId());
        return ctx.proceed();
    }
}
