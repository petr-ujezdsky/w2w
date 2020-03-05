package com.foo.servlet;

import com.foo.EjbLocator;
import com.foo.MyService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/testException")
public class TestExceptionServlet extends LoggingServlet {

    private final MyService myServiceRemote;

    public TestExceptionServlet() {
        myServiceRemote = EjbLocator.INSTANCE.lookupBean(MyService.class);
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
