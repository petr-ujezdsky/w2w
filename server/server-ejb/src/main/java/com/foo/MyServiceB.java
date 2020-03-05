package com.foo;

import javax.ejb.Local;

/**
 * MyServiceB local interface
 */
@Local
public interface MyServiceB {
    String processText(String text);
}
