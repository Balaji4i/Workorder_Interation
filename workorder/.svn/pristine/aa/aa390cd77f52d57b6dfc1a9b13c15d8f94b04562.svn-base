/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scm.main;

import com.scm.Database.CallDBQuery;
import com.scm.payload.InvokePostRequest;
import com.scm.payload.InvokeWebservices;
import com.scm.payload.RestCallServices;
import com.scm.payload.invokeOkHttps;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author DineshkumarP
 */
public class MainFile {
 
    public static void main(String[] args) throws JSONException, IOException, Exception {
        
        HashMap<String, String> details=CallDBQuery.getTransferWorkOrder();
        String hdrId=details.get("HdrId")==null?"0":details.get("HdrId").toString();
        System.err.println("==>HdrId==>"+hdrId);
        ArrayList ls = new ArrayList();        
        if(hdrId.equalsIgnoreCase("0")){
            System.err.println("==>HdrId<==");
        }else{
            System.err.println("HdrId==>"+details.get("HdrId"));
            System.err.println("ResourceTableId==>"+details.get("ResourceTableId"));
            System.err.println("PayloadType==>"+details.get("PayloadType"));   
        
           if(details.get("PayloadType").equalsIgnoreCase("NEW")){
               
               String workOrderId=details.get("WorkOrderId")==null?"0":details.get("WorkOrderId").toString();
               String operationId=details.get("OperationId")==null?"0":details.get("OperationId").toString();
               String resourceId=details.get("ResourceTableId")==null?"0":details.get("ResourceTableId").toString();
               String seqHdrId=details.get("HdrId")==null?"0":details.get("HdrId").toString();
               
//               RestCallServices.postService(workOrderId, operationId, json.toString());
                 ls=(ArrayList) invokeOkHttps.callPostServices(workOrderId, operationId, getPostPayroll(details));
                 
                 if(ls.get(0).toString().equalsIgnoreCase("SUCCESS")){
                    String resourcesId=ls.get(1).toString();
                    System.err.println("resourcesId==>"+resourcesId);
                 }else{
                    String message=ls.get(1).toString();
                    System.err.println("resourcesId==>"+message); 
                 }
                //===================                 
                CallDBQuery.updateStatus(ls.get(0).toString(), ls.get(1).toString(), seqHdrId, resourceId);
           }else{
                // Update                

           } 
        
        }
        
    }
    
    public static String getPostPayroll(HashMap<String, String> details){
      String json=
                "{\r\n\""
              + "BasisType\": \""+details.get("BasisType").toString()+"\","
              + "\r\n\"ResourceCode\":\""+details.get("ResourceCode").toString()+"\","
              + "\r\n\"RequiredUsage\":\""+details.get("RequiredUsage").toString()+"\","
              + "\r\n\"ResourceSequenceNumber\":\""+details.get("ResourceSequenceNumber").toString()+"\","
              + "\r\n\"UsageRate\":\""+details.get("UsageRate").toString()+"\","
              + "\r\n\"PlannedStartDate\": \""+details.get("PlannedStartDate").toString()+"\","
              + "\r\n\"PlannedCompletionDate\": \""+details.get("PlannedCompletionDate").toString()+"\""
              + "\r\n}";
//      System.err.println("Sample Payload==>"+json);
      return json;
    }
    
    
    public String getPatchPayroll(HashMap<String, String> details){
      String json=
                "{\r\n\""
              + "WoOperationResourceId\": \""+details.get("OperationResourceId").toString()+"\","
              + "\r\n\"ResourceId\":\""+details.get("ResourceId").toString()+"\","
              + "\r\n\"RequiredUsage\":\""+details.get("RequiredUsage").toString()+"\","
              + "\r\n\"ChargeType\":\"AUTOMATIC\","
              + "\r\n\"PlannedStartDate\": \""+details.get("PlannedStartDate").toString()+"\","
              + "\r\n\"PlannedCompletionDate\": \""+details.get("PlannedCompletionDate").toString()+"\""
              + "\r\n}";
//      System.err.println("Sample Payload==>"+json);
      return json;
    }
    
}
