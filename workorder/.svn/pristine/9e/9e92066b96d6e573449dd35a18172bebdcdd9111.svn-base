/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scm.payload;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Base64;

public class RestCallServices {

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
    
    
    /* Mehod is used for calling REST Webservice*/
    public static String postService(String workOrder, String operationId, String jsonRequest) throws Exception {
        String outputString = "";
        //
        String userName="erp_user";
        String passWord="welcome@4i";
        
        HttpURLConnection conn = null;
        try {
            String restServiceURL = getWebServices(workOrder, operationId);
            System.out.println("URL==>"+restServiceURL);
            URL url = new URL(restServiceURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

//            String auth = userName + ":" + passWord;
//            BASE64Encoder encoder = new BASE64Encoder();
//            String authString = encoder.encode(auth.getBytes());

//          String encoded = Base64.getEncoder().encodeToString((userName+":"+passWord).getBytes(StandardCharsets.UTF_8));  //Java 8
//          byte[] message = (userName+":"+passWord).getBytes("UTF-8");
//          String encoded = javax.xml.bind.DatatypeConverter.printBase64Binary(message);
//          conn.setRequestProperty("Authorization", "Basic "+encoded);
            
//            String auth = userName + ":" + passWord;
//            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8));
//            String authHeaderValue = "Basic " + new String(encodedAuth);
//            conn.setRequestProperty("Authorization", authHeaderValue);
            
            String base64encodedString = Base64.getEncoder().encodeToString((userName+":"+passWord).getBytes("utf-8"));
            conn.setRequestProperty("Authorization", "Basic " +base64encodedString);

            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Language", "en-US");
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(10000);


            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(jsonRequest.toCharArray());
            wr.flush();
            wr.close();
            conn.connect();

            System.out.println("\nJason Request:\n " + jsonRequest);
            System.out.println("\nresponse code :" + conn.getResponseCode());
            System.out.println("conn.getResponseCode() : " + conn.getResponseCode());
            System.out.println("conn.getErrorStream() : " + conn.getErrorStream());
            System.out.println(conn.getResponseMessage());

            if (conn.getResponseCode() == 201) {
                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                String output = "";
                while ((output = br.readLine()) != null) {
                    outputString += output;
                }
            } else if (conn.getResponseCode() == -1 || conn.getResponseCode() == 400) {
                BufferedReader br  = null;
                try {
                    br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
                } catch(Exception e) {
                    br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
                }
                
                String output = "";
                outputString += "ERROR:";
                while ((output = br.readLine()) != null) {
                    outputString += output;
                }
            } else {
                outputString += "ERROR: " + conn.getResponseCode() + " " + conn.getResponseMessage();
            }
            conn.disconnect();
        } catch (Exception e) {
            throw e;
        }
        return outputString;
    }
}
