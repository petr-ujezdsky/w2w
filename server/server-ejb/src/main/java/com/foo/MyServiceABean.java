package com.foo;

import com.foo.interceptor.MyContextDataRetrievingServerInterceptor;
import com.foo.interceptor.AroundLoggingServerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

/**
 * MyServiceA implementation
 */
@Stateless
@Interceptors({
        AroundLoggingServerInterceptor.class,
        MyContextDataRetrievingServerInterceptor.class
})
public class MyServiceABean implements MyServiceA {

    private static final Logger logger = LoggerFactory.getLogger(MyServiceABean.class);

    @EJB
    private MyServiceBBean myServiceB;

    @Override
    public String processText(String text) {
        logger.info("Doing \"{}\".toUpperCase() inside method {}", text, "processText");
        return text.toUpperCase();
    }

    @Override
    public String alwaysFail() throws Exception {
        logger.info("Throwing exception");
        throw new Exception("Intended exception for test");
    }

    private String localMethod(String text) {
        logger.info("Doing \"{}\".toUpperCase() inside method {}", text, "localMethod");
        return text.toUpperCase();
    }

    @Override
    public String callsALocal(String text) {
        logger.info("Invoking local {}#localMethod", getClass().getSimpleName());
        return localMethod(text);
    }

    @Override
    public String callsARemoteProcessText(String text) {
        logger.info("Invoking remote {}#processText", getClass().getSimpleName());
        return processText(text);
    }

    @Override
    public String callsBRemoteProcessText(String text) {
        logger.info("Invoking remote {}#processText", myServiceB.getClass().getSimpleName());
        return myServiceB.processText(text);
    }
}
