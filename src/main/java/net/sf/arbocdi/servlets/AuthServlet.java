/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.arbocdi.servlets;

import net.sf.arbocdi.client.HttpRequests;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.arbocdi.AppConstants;

/**
 *
 * @author root
 */
public class AuthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        session.setAttribute("name", req.getParameter("name"));
        HttpRequests requests = new HttpRequests();
        try {
            URI uri = requests.buildCodeUri(AppConstants.C_ID, AppConstants.REDIRECT_URL);
            resp.sendRedirect(uri.toString());
        } catch (URISyntaxException ex) {
            throw new ServletException(ex);
        }
    }
    
}
