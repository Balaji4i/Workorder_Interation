/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scm.payload;

import static com.scm.payload.InvokeWebservices.getWebServices;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
//import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author DineshkumarP
 */
public class InvokePostRequest {
    
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
    
    
//    public static JSONObject setPost(String workOrderId, String operationId, JSONObject json) throws MalformedURLException, IOException, JSONException{
//
//        String user_name="erp_user";
//        String password="welcome@4i";
//        
//        String url=getWebServices(workOrderId, operationId);
//        
//         URL obj = new URL(url);
//         HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
//         con.setRequestMethod("POST");
//
//
//         String userCredentials = user_name+":"+password;
//         String basicAuth = "Basic " + new String(new Base64().encode(userCredentials.getBytes()));
//         con.setRequestProperty ("Authorization", basicAuth);
//         con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64; rv:27.0) Gecko/20100101 Firefox/27.0.2 Waterfox/27.0");
//         con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//         String urlParameters = "param1=value1&param2=value2";
//         // Send post request
//         con.setDoOutput(true);
//         DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//         wr.writeBytes(urlParameters);
//         wr.flush();
//         wr.close();
//         int responseCode = con.getResponseCode();
//         System.out.println("\nSending 'POST' request to URL : " + url);
//         System.out.println("Post parameters : " + urlParameters);
//         System.out.println("Response Code : " + responseCode);
//         BufferedReader in = new BufferedReader(
//         new InputStreamReader(con.getInputStream()));
//         String inputLine;
//         StringBuffer response = new StringBuffer();
//         while ((inputLine = in.readLine()) != null) {
//          response.append(inputLine);
//         }
//         in.close();
//         //print result
//         System.out.println(response.toString());   
//        
//   
//    // below code converts the json response to json object and reads each values
//        JSONObject jsonObj = new JSONObject(response.toString());
//        String access_token =jsonObj.getString("access_token");
//        System.out.println("access_token : "+access_token);
//   
//        return null;
//    }
    
}
