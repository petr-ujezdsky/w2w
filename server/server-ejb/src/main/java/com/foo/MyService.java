package com.foo;

import javax.ejb.Remote;

/**
 * MyService remote interface
 */
@Remote
public interface MyService {
    String processText(String text);
}
