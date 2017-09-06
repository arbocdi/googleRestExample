/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.arbocdi.servlets;

import net.sf.arbocdi.client.HttpRequests;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import net.sf.arbocdi.AppConstants;

/**
 *
 * @author root
 */
@Log4j2
public class Oauth2CallbackServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        if (code != null) {
            resp.getWriter().println("got access code " + code);
        } else {
            resp.getWriter().println("Error getting access code");
        }
        HttpRequests requests = new HttpRequests();
        GoogleTokenResponse token = requests.sendAuthRequest(AppConstants.C_ID, AppConstants.C_SEC, AppConstants.REDIRECT_URL, code);
        resp.getWriter().print(token);
        HttpSession session = req.getSession();
        session.setAttribute("token", token);
        requests.postFile(token.getAccess_token(), AppConstants.XLSX_CONTENT_TYPE,"response.xlsx" );
    }

}
