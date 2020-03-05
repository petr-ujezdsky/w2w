package com.foo.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class LoggingServlet extends HttpServlet {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public final void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Processing request - start");
        doGetImpl(request, response);
        logger.info("Processing request - end");
    }

    protected abstract void doGetImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}