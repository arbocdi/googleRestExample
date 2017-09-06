/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.arbocdi.client;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

/**
 *
 * @author root
 */
public class ExternalRedirectStrategy implements RedirectStrategy{

    @Override
    public boolean isRedirected(HttpRequest arg0, HttpResponse arg1, HttpContext arg2) throws ProtocolException {
        return false;
    }

    @Override
    public HttpUriRequest getRedirect(HttpRequest arg0, HttpResponse arg1, HttpContext arg2) throws ProtocolException {
        return null;
    }
    
}
