/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.arbocdi.client;

import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import net.sf.selibs.utils.misc.HttpClientWrapper;
import net.sf.selibs.utils.service.ServiceException;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 *
 * @author root
 */
public class HttpLauncher {

    @Getter
    private static HttpClientWrapper client;

    static {
        try {
            client = new HttpClientWrapper() {
                @Override
                public void start() throws ServiceException {
//        PoolingHttpClientConnectionManager is a more complex implementation that 
//        manages a pool of client connections and is able to service connection requests 
//        from multiple execution threads. Connections are pooled on a per route basis. 
//        A request for a route for which the manager already has a persistent connection 
//        available in the pool will be serviced by leasing a connection from the pool 
//        rather than creating a brand new connection.
                    cm = new PoolingHttpClientConnectionManager();
                    // Increase max total connection to 200
                    cm.setMaxTotal(this.maxConnections);
                    // Increase default max connection per route to 20
                    cm.setDefaultMaxPerRoute(this.maxConnectionsPerRoute);
//        HttpClient implementations are expected to be thread safe. 
//        It is recommended that the same instance of this class is reused 
//        for multiple request executions.
                    httpClient = HttpClients.custom()
                            .setConnectionManager(cm).setRetryHandler(new HttpClientWrapper.SafeRetryHandler()).
                            setRedirectStrategy(new ExternalRedirectStrategy())
                            .build();
                    super.start();
                }
            };

            client.start();
        } catch (ServiceException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                client.stop();
            }

        });
    }
}
