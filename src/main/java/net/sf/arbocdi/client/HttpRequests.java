/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.arbocdi.client;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import net.sf.arbocdi.AppConstants;
import net.sf.arbocdi.servlets.GoogleTokenResponse;
import net.sf.arbocdi.servlets.GoogleTokenResponse;
import net.sf.arbocdi.servlets.MyUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author root
 */
public class HttpRequests {

    public URI buildCodeUri(String appClientId, String redirectUrl) throws URISyntaxException {
        URIBuilder builder = new URIBuilder();
        builder.setScheme("https").setHost("accounts.google.com").setPath("/o/oauth2/v2/auth")
                .setParameter("scope", "https://www.googleapis.com/auth/drive")
                .setParameter("response_type", "code")
                .setParameter("client_id", appClientId)
                .setParameter("redirect_uri", redirectUrl);
        return builder.build();
    }

    public GoogleTokenResponse sendAuthRequest(String appClientId, String appSecret, String redirectUrl, String code) throws UnsupportedEncodingException, IOException {
        HttpPost post = new HttpPost("https://www.googleapis.com/oauth2/v4/token");
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(5000)
                .build();
        post.setConfig(defaultRequestConfig);
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        List<NameValuePair> params = new LinkedList();
        params.add(new BasicNameValuePair("code", code));
        params.add(new BasicNameValuePair("client_id", appClientId));
        params.add(new BasicNameValuePair("client_secret", appSecret));
        params.add(new BasicNameValuePair("redirect_uri", redirectUrl));
        params.add(new BasicNameValuePair("grant_type", "authorization_code"));
        post.setEntity(new UrlEncodedFormEntity(params));

        GoogleTokenResponse token = null;
        try (CloseableHttpResponse response = HttpLauncher.getClient().getHttpClient().execute(post)) {
            token = AppConstants.OM.readValue(response.getEntity().getContent(), GoogleTokenResponse.class);
        }
        return token;
    }

    public void postFile(String authToken, String contentType, String fileNme) throws IOException {
        //post file metadata
        HttpPost post = new HttpPost("https://www.googleapis.com/drive/v2/files");

        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(5000)
                .build();
        post.setConfig(defaultRequestConfig);
        post.addHeader("Authorization","Bearer "+ authToken);
        post.addHeader("Content-type","application/json");
        GoogleFileDescriptor fileDescr = new GoogleFileDescriptor(fileNme, contentType);
        StringEntity json = new StringEntity(AppConstants.OM.writeValueAsString(fileDescr));
        post.setEntity(json);
        
        try (CloseableHttpResponse response = HttpLauncher.getClient().getHttpClient().execute(post)) {
            fileDescr = AppConstants.OM.readValue(response.getEntity().getContent(),GoogleFileDescriptor.class);
        }
        //put file contents
        String url="https://www.googleapis.com/upload/drive/v2/files/#id#?uploadType=media"
                .replaceAll("#id#", fileDescr.getId());
        HttpPut put = new HttpPut(url);
        put.setConfig(defaultRequestConfig);
        put.addHeader("Authorization","Bearer "+ authToken);
        put.addHeader("Content-Type", contentType);
        FileEntity file = new FileEntity(new File(fileNme));
        put.setEntity(file);
        try (CloseableHttpResponse response = HttpLauncher.getClient().getHttpClient().execute(put)) {
            System.out.println(response.getStatusLine());
        }
    }
}
