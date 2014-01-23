/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integralads.poc.servlets;

import com.integralads.poc.baseItems.UidBaseElementManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *@WebServlet(name = "ObjectLoader", urlPatterns = {"/ObjectLoader"}, initParams = {
    @WebInitParam(name = "load-on-startup", value = "1")})
 * @author geoff
 */

@WebServlet(value="/ObjectLoader", loadOnStartup=1)
public class ObjectLoader extends HttpServlet {
    
    UidBaseElementManager mgr;

    @Override
    public void init(ServletConfig config) throws ServletException {
        
        super.init(config);

        mgr = new UidBaseElementManager();
        ServletContext sc = config.getServletContext();
        // sc.setAttribute(servletName+"/stats/Hits this minute", hitsThis);
        sc.setAttribute("BOT_MANAGER", mgr);
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ObjectLoader</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<H1>Number of requests: " + mgr.handledEvents() + " </H1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
