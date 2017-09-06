/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.arbocdi;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.sf.arbocdi.client.GoogleFileDescriptor;
import net.sf.arbocdi.servlets.MyUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.junit.Test;

/**
 *
 * @author root
 */
public class MultipartTest {
    @Test
    public void testMultipart() throws Exception{
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.STRICT);
        builder.setContentType(ContentType.create("multipart/related"));
        builder.setBoundary("---boundry---");
        GoogleFileDescriptor fileDescr = new GoogleFileDescriptor();
        fileDescr.setTitle("response.xlsx");
        builder.addTextBody("nme", 
                AppConstants.OM.writerWithDefaultPrettyPrinter().writeValueAsString(fileDescr), 
                ContentType.APPLICATION_JSON);
    }
}
