package com.foo;

import com.foo.interceptor.MyContextDataRetrievingInterceptor;
import com.foo.interceptor.MyServerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 * MyService implementation
 */
@Stateless
@Interceptors({
        MyContextDataRetrievingInterceptor.class,
        MyServerInterceptor.class
})
public class MyServiceBean implements MyService {

    private static final Logger logger = LoggerFactory.getLogger(MyServiceBean.class);

    @Override
    public String processText(String text) {
        logger.info("Doing textToUpper for text " + text);
        return text.toUpperCase();
    }
}
