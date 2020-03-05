package com.foo.servlet;

import com.foo.EjbLocator;
import com.foo.MyServiceA;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/testA2BRemoteCall")
public class TestA2BRemoteCallServlet extends LoggingServlet {

    private final MyServiceA myServiceRemote;

    public TestA2BRemoteCallServlet() {
        myServiceRemote = EjbLocator.INSTANCE.lookupBean(MyServiceA.class);
    }

    public void doGetImpl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String text = "hello world";
        String processedText = myServiceRemote.callsBRemoteProcessText(text);

        PrintWriter out = response.getWriter();
        out.println("EJB call performed. Result:");
        out.println(processedText);
        out.flush();
        out.close();
    }
}
