package com.foo;

import java.io.Serializable;
import java.util.UUID;

/**
 * Context data to transfer between client and server.
 */
public class MyContextData implements Serializable {

    private final UUID requestId;

    public MyContextData(UUID requestId) {
        this.requestId = requestId;
    }

    public UUID getRequestId() {
        return requestId;
    }
}
