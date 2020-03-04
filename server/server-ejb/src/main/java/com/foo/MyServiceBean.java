package com.foo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;

/**
 * MyService implementation
 */
@Stateless
public class MyServiceBean implements MyService {

    private static final Logger logger = LoggerFactory.getLogger(MyServiceBean.class);

    @Override
    public String processText(String text) {
        logger.info("Doing textToUpper for text " + text);
        return text.toUpperCase();
    }
}
