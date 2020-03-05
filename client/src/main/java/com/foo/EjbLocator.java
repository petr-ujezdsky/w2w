package com.foo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * Locator for EJB services.
 */
public class EjbLocator {

    private static final Logger logger = LoggerFactory.getLogger(EjbLocator.class);

    public static final EjbLocator INSTANCE = new EjbLocator();

    private final Context ctx;

    private EjbLocator() {
        try {
            ctx = createInitialContext();
        } catch (NamingException e) {
            throw new RuntimeException("Could not create JNDI context", e);
        }
    }

    @SuppressWarnings("unchecked")
    public  <T> T lookupBean(Class<T> clazz) {
        final String appName = "server-ear";
        final String moduleName = "server-ejb";
        final String distinctName = "";
        final String viewClassName = clazz.getName();
        String beanName = clazz.getSimpleName() + "Bean";

        String jndiName = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName;

        logger.info("Used JNDI name " + jndiName);
        try {
            return (T) ctx.lookup(jndiName);
        } catch (NamingException e) {
            throw new IllegalArgumentException("Class " + clazz.getName() + " has no EJB server counterpart", e);
        }
    }

    private Context createInitialContext() throws NamingException {
        Properties prop = new Properties();

        prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        prop.put(Context.PROVIDER_URL, "http-remoting://w2w-server:8080");

        return new InitialContext(prop);
    }
}
