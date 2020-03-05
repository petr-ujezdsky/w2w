package com.foo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/test")
public class MyServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(MyServlet.class);

    private final MyService myServiceRemote;

    public MyServlet() {
        myServiceRemote = EjbLocator.INSTANCE.lookupBean(MyService.class);
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
}
