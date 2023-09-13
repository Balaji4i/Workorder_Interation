/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scm.payload;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DineshkumarP
 */
public class invokeOkHttps {

    
    public static String getWebServices(String workOrderId, String operationId){
       String url="https://egzy-test.fa.em2.oraclecloud.com/"
               + "fscmRestApi/resources/11.13.18.05/"
               + "maintenanceWorkOrders/"+workOrderId
               + "/child/WorkOrderOperation/"+operationId
               + "/child/WorkOrderOperationResource";
       System.err.println("Post url==>"+url);
       return url;
    }
    
    
    public static String getPatchWebServices(String workOrderId, String operationId, String operationResourceId){
       String url=       
                "https://egzy-test.fa.em2.oraclecloud.com/"
               + "fscmRestApi/resources/11.13.18.05/"
               + "maintenanceWorkOrders/"+workOrderId
               + "/child/WorkOrderOperation/"+operationId
               + "/child/WorkOrderOperationResource/"+operationResourceId;
       System.err.println("Patch url==>"+url);
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
    
    
    
   public static List<String> callPostServices(String workOrderId, String operationId, String JsonData) throws IOException, JSONException{
       List<String> ls = new ArrayList<>();
       OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        
//        System.err.println("==>Json Data==>"+JsonData);   
        RequestBody body = RequestBody.create(mediaType, JsonData);
        Request request = new Request.Builder()
        .url(getWebServices(workOrderId, operationId))
        .method("POST", body)
        .addHeader("Authorization", "Basic ZXJwX3VzZXI6d2VsY29tZUA0aQ==")
        .addHeader("Content-Type", "application/json")
        .addHeader("Cookie", "ak_bmsc=CD6B4784001D63E9435080C69BEB9B65~000000000000000000000000000000~YAAQXNs4fcYbe+Z9AQAAMm0iHw4akOPVBo8lsymMXUqKZHG0oCR/x13o5aJltuwc/P5BBJ+lOZW08f39nBfaiA/lX2pV89C27DsIqlOI19voffu7RSpUUu49CGtGN2ySt4MASoZTaOstg+k3aZjr841befv8VH8j7IMoG9BKp3GiiVgmvRYB2z4XEbOgdD1Bfu2bIpJOq8R0he98Ckmi7H0jVtxEQcT8928AOm0LzrP64caT2Mh0NJAxj9h5ALMnUMUbqdZwJ9q2jOivxxb6kqRJR103OA5Yu6GzDgg3olM0j0+Y/WdcBvx9nroBcOK9npooQTNLQgoWPICnns6uXY+3DNNrLWOYA+a9Hnc4tHR9nWqCi0sZhnoMNQ7SPdDHuXNjsjgRSJcy")
        .build();
         Response response = client.newCall(request).execute();
         String jsonData = response.body().string();
         
         if(response.code()==201){
            JSONObject Jobject = new JSONObject(jsonData);
            Jobject.get("WoOperationResourceId");
//            System.err.println("WoOperationResourceId==>"+Jobject.get("WoOperationResourceId"));
            ls.add("SUCCESS");
            ls.add(Jobject.get("WoOperationResourceId").toString());
            return ls; 
         }else{
//            System.err.println("jsonData==>"+jsonData);
            ls.add("ERROR");
            ls.add(jsonData);
            return ls;
         }
//         System.err.println("response==>"+response);  
//         System.err.println("jsonData==>"+jsonData);   
    
   }
    
   
   
   public static List<String> callPatchServices(String workOrderId, String operationId, String  OperationResourceIdId,  String JsonData) throws IOException, JSONException{
       List<String> ls = new ArrayList<>();
       OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        
        System.err.println("==>Json Data==>"+JsonData);   
        RequestBody body = RequestBody.create(mediaType, JsonData);
        Request request = new Request.Builder()
        .url(getPatchWebServices(workOrderId, operationId, OperationResourceIdId))
        .method("PATCH", body)
        .addHeader("Authorization", "Basic ZXJwX3VzZXI6d2VsY29tZUA0aQ==")
        .addHeader("Content-Type", "application/json")
        .addHeader("Cookie", "ak_bmsc=CD6B4784001D63E9435080C69BEB9B65~000000000000000000000000000000~YAAQXNs4fcYbe+Z9AQAAMm0iHw4akOPVBo8lsymMXUqKZHG0oCR/x13o5aJltuwc/P5BBJ+lOZW08f39nBfaiA/lX2pV89C27DsIqlOI19voffu7RSpUUu49CGtGN2ySt4MASoZTaOstg+k3aZjr841befv8VH8j7IMoG9BKp3GiiVgmvRYB2z4XEbOgdD1Bfu2bIpJOq8R0he98Ckmi7H0jVtxEQcT8928AOm0LzrP64caT2Mh0NJAxj9h5ALMnUMUbqdZwJ9q2jOivxxb6kqRJR103OA5Yu6GzDgg3olM0j0+Y/WdcBvx9nroBcOK9npooQTNLQgoWPICnns6uXY+3DNNrLWOYA+a9Hnc4tHR9nWqCi0sZhnoMNQ7SPdDHuXNjsjgRSJcy")
        .build();
         Response response = client.newCall(request).execute();
         String jsonData = response.body().string();
         System.err.println("jsonData ::"+jsonData);
         System.err.println("response.code() ::"+response.code());
         if(response.code()==200){
            JSONObject Jobject = new JSONObject(jsonData);
            Jobject.get("WoOperationResourceId");
//            System.err.println("WoOperationResourceId==>"+Jobject.get("WoOperationResourceId"));
            ls.add("SUCCESS");
            ls.add(Jobject.get("WoOperationResourceId").toString());
            System.err.println("inside callPatchServices method ok==>");
            return ls; 
         }else{
//            System.err.println("jsonData==>"+jsonData);
            ls.add("ERROR");
            ls.add(jsonData);
            System.err.println("inside callPatchServices method ERROR==>");
            return ls;
         }
//         System.err.println("response==>"+response);  
//         System.err.println("jsonData==>"+jsonData);   
    
   }
   
   
    
    
}
