package com.foo.servlet;

import com.foo.EjbLocator;
import com.foo.MyServiceA;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/testException")
public class TestExceptionServlet extends LoggingServlet {

    private final MyServiceA myServiceRemote;

    public TestExceptionServlet() {
        myServiceRemote = EjbLocator.INSTANCE.lookupBean(MyServiceA.class);
    }

    public void doGetImpl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            myServiceRemote.alwaysFail();
            throw new RuntimeException("Ejb call did not fail");
        } catch (Exception e) {
            // expected exception
            logger.info("Ejb call threw exception", e);
        }
        PrintWriter out = response.getWriter();
        out.println("EJB call performed.");
        out.flush();
        out.close();
    }
}
