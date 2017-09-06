/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.arbocdi.servlets;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 *
 * @author root
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleTokenResponse {
   private String access_token;
   private int expires_in;
   private String token_type;
   private String refresh_token;
   private String error;
   private String error_description;
}
