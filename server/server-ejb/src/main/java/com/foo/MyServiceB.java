package com.foo;

import javax.ejb.Remote;

/**
 * MyServiceB remote interface
 */
@Remote
public interface MyServiceB {
    String processText(String text);
}
