package com.foo;

import com.foo.interceptor.AroundLoggingServerInterceptor;
import com.foo.interceptor.MyContextDataRetrievingServerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 * MyServiceB implementation
 */
@Stateless
@Interceptors({
        AroundLoggingServerInterceptor.class,
        MyContextDataRetrievingServerInterceptor.class
})
public class MyServiceBBean implements MyServiceB {

    private static final Logger logger = LoggerFactory.getLogger(MyServiceBBean.class);

    @Override
    public String processText(String text) {
        logger.info("Doing \"{}\".toLowerCase() inside method {}", text, "processText");
        return text.toLowerCase();
    }
}
