/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integralads.poc.servlets;

import com.integralads.poc.baseItems.UidBaseElementManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author geoff
 */
// @WebServlet(name = "OccurenceRecorder", urlPatterns = {"/OccurenceRecorder"})
@WebServlet(value="/OccurenceRecorder", loadOnStartup=2)
public class OccurenceRecorder extends HttpServlet {
    
    private UidBaseElementManager botmgr;
    
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        
        ServletContext sc = config.getServletContext();
        botmgr = (UidBaseElementManager) sc.getAttribute("BOT_MANAGER");
        if (botmgr == null) {
            System.err.println("Cound not obtain BOT MANAGER from the servlet context");
        }
        else {
            System.err.println("Successfully retrieved BOT MANAGER");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        long userId = Long.valueOf(request.getParameter("uid"));
        
        long startTime = System.nanoTime();
        
        boolean isBot = botmgr.isBot(userId);
        long nano = System.nanoTime() - startTime;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("Is a bot " + Boolean.toString(isBot));
        } finally {            
            out.close();
        }
        
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OccurenceRecorder</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OccurenceRecorder at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }
}
