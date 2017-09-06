/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.arbocdi.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

/**
 *
 * @author root
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class GoogleFileDescriptor {
    private String title;
    private String mimeType;
    private String id;

    public GoogleFileDescriptor(String title, String mimeType) {
        this.title = title;
        this.mimeType = mimeType;
    }

    public GoogleFileDescriptor() {
    }
    
    
}
