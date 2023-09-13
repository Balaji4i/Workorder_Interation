/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scm.payload;

//import okhttp3.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;
import java.util.Base64;
/**
 *
 * @author DineshkumarP
 */
public class InvokeWebservices {
    
    
    public static String getWebServices(String workOrderId, String operationId){
    String url="https://egzy-test.fa.em2.oraclecloud.com/"
            + "fscmRestApi/resources/11.13.18.05/"
            + "maintenanceWorkOrders/"+workOrderId
            + "/child/WorkOrderOperation/"+operationId
            + "/child/WorkOrderOperationResource";
    return url;
    }
    
//    public static String getWebServices(String workOrder, String operationId){
//    String url="https://egzy.fa.em2.oraclecloud.com/"
//            + "fscmRestApi/resources/11.13.18.05/"
//            + "maintenanceWorkOrders/"+workOrder
//            + "/child/WorkOrderOperation/"+operationId
//            + "/child/WorkOrderOperationResource";
//    return url;
//    }       
            
          
            
    public static String PostRequest(String workOrderId, String operationId, JSONObject json) throws MalformedURLException, IOException{
        
        String userName="erp_user";
        String passWord="welcome@4i";
        
        String urlapi=getWebServices(workOrderId, operationId);
        
        URL url = new URL (urlapi);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        
        String encoded = Base64.getEncoder().encodeToString((userName+":"+passWord).getBytes(StandardCharsets.UTF_8));  //Java 8
        
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        con.setRequestProperty("Authorization", "Basic "+encoded);
                
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = json.toString().getBytes("utf-8");
            os.write(input, 0, input.length);			
        }
        //            
        try(BufferedReader br = new BufferedReader(
            new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println("OUT==>"+response.toString());
        }
        
        
      return null;  
    }
    
}
