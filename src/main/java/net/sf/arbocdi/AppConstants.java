/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.arbocdi;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author root
 */
public interface AppConstants {
    String C_ID="578916303787-7fe1vcvejtl562oqs0qtq0k06hfju3cj.apps.googleusercontent.com";
    String C_SEC="PnQiWa9DeVanMj2BW-bGP2-3";
    String REDIRECT_URL="http://127.0.0.1:8888/oauth2Callback"; 
    String REDIRECT_URL2="http://127.0.0.1:8888/action"; 
    ObjectMapper OM = new ObjectMapper();
    
    String UPLOAD_POST_URL="https://www.googleapis.com/upload/drive/v2/files?uploadType=multipart";
    String XLSX_CONTENT_TYPE="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
}
