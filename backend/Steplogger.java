package com.example.tlee1.myapplication.backend;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;



/**
 * Created by tlee1 on 6/8/2016.
 */
@SuppressWarnings("serial")
public class Steplogger extends HttpServlet{
    private static final Logger _logger =
            Logger.getLogger(Steplogger.class.getName());
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException {

        try{
            _logger.info("Cron Job has been executed");
        }
        catch (Exception ex) {
    }
}

@Override
public void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
doGet(req, resp);
}
    }
