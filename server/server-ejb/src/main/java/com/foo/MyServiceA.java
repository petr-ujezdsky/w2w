package com.foo;

import javax.ejb.Remote;

/**
 * MyServiceA remote interface
 */
@Remote
public interface MyServiceA {
    String processText(String text);

    String alwaysFail() throws Exception;

    String callsALocal(String text);

    String callsARemoteProcessText(String text);

    String callsBRemoteProcessText(String text);
}
