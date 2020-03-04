package com.foo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

@WebServlet(urlPatterns = "/test")
public class MyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(MyServlet.class);

    private final MyService myServiceRemote;

    public MyServlet() throws NamingException {
        myServiceRemote = lookupBean(MyService.class);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Processing request - start");
        String text = "hello world";
        String processedText = myServiceRemote.processText(text);

        PrintWriter out = response.getWriter();
        out.println("EJB call performed. Result:");
        out.println(processedText);
        out.flush();
        out.close();
        logger.info("Processing request - end");
    }

    @SuppressWarnings("unchecked")
    private <T> T lookupBean(Class<T> clazz) throws NamingException {
        Context ctx = createInitialContext();

        final String appName = "server-ear";
        final String moduleName = "server-ejb";
        final String distinctName = "";
        final String viewClassName = clazz.getName();
        String beanName = clazz.getSimpleName() + "Bean";

        String jndiName = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName;

        logger.info("Used JNDI name " + jndiName);
        return (T) ctx.lookup(jndiName);
    }

    private Context createInitialContext() throws NamingException {
        Properties prop = new Properties();

        prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        prop.put(Context.PROVIDER_URL, "http-remoting://w2w-server:8080");

        return new InitialContext(prop);
    }
}
