package com.foo;

import com.foo.interceptor.MyContextDataRetrievingServerInterceptor;
import com.foo.interceptor.AroundLoggingServerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 * MyService implementation
 */
@Stateless
@Interceptors({
        AroundLoggingServerInterceptor.class,
        MyContextDataRetrievingServerInterceptor.class
})
public class MyServiceBean implements MyService {

    private static final Logger logger = LoggerFactory.getLogger(MyServiceBean.class);

    @Override
    public String processText(String text) {
        logger.info("Doing textToUpper for text " + text);
        return text.toUpperCase();
    }
}
