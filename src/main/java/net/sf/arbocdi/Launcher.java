/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.arbocdi;

import java.io.IOException;
import java.io.PrintStream;
import lombok.extern.log4j.Log4j2;
import net.sf.arbocdi.servlets.ActionServlet;
import net.sf.arbocdi.servlets.AuthServlet;
import net.sf.arbocdi.servlets.Oauth2CallbackServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.RolloverFileOutputStream;

/**
 *
 * @author root
 */

public class Launcher {
    public static void main(String[] args) throws IOException, InterruptedException, Exception {
      
        
         Server server = new Server(8888);

        ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.setResourceBase(System.getProperty("java.io.tmpdir"));
        server.setHandler(context);
        // Add default servlet
        context.addServlet(AuthServlet.class, "/auth");
        context.addServlet(Oauth2CallbackServlet.class, "/oauth2Callback");
         context.addServlet(ActionServlet.class, "/action");

        server.start();
        server.join();
    }
   
}
