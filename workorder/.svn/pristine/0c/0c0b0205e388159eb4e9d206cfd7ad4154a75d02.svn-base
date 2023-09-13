/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scm.restapi;
import com.scm.Database.CallDBQuery;
import com.scm.main.MainFile;
import static com.scm.main.MainFile.getPostPayroll;
import com.scm.payload.invokeOkHttps;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author DineshkumarP
 */
@Path("resourcecreation")
public class ResourcesCreation {
    
    // http://localhost:8080/woServices/webresources/resourcecreation/v1?resourceId=2

    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of InvoiceCreation
     */
    public ResourcesCreation() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
//        throw new UnsupportedOperationException();
        return null;
    }
    

    @Path("/v1")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createInvoice(
            @QueryParam(value = "resourceId") String resourceId
                )throws IOException, JSONException, SQLException, NamingException, ClassNotFoundException {
        String resultstatus="n";
        HashMap<String, String> details=CallDBQuery.getTransferWorkOrderById(resourceId);
        
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
               String resId=details.get("ResourceTableId")==null?"0":details.get("ResourceTableId").toString();
               String seqHdrId=details.get("HdrId")==null?"0":details.get("HdrId").toString();
               
//               RestCallServices.postService(workOrderId, operationId, json.toString());
                 ls=(ArrayList) invokeOkHttps.callPostServices(workOrderId, operationId, getPostPayroll(details));
                 
                 if(ls.get(0).toString().equalsIgnoreCase("SUCCESS")){
                    String resourcesPkId=ls.get(1).toString();
                    System.err.println("resourcesId==>"+resourcesPkId);
                 }else{
                    String message=ls.get(1).toString();
                    System.err.println("resourcesId==>"+message); 
                 }
                //===================                 
               resultstatus=CallDBQuery.updateStatus(ls.get(0).toString(), ls.get(1).toString(), seqHdrId, resId);
           }else{

                // Update - start               
               
               MainFile mf= new MainFile();

               String workOrderId=details.get("WorkOrderId")==null?"0":details.get("WorkOrderId").toString();
               String operationId=details.get("OperationId")==null?"0":details.get("OperationId").toString();
               String OperationResourceIdId=details.get("OperationResourceId")==null?"0":details.get("OperationResourceId").toString();
               String rscId=details.get("ResourceId")==null?"0":details.get("ResourceId").toString();//only resource id not wo op resource id as like seq
               String resId=details.get("ResourceTableId")==null?"0":details.get("ResourceTableId").toString();
               String seqHdrId=details.get("HdrId")==null?"0":details.get("HdrId").toString();
               System.err.println("workOrderId u==>"+workOrderId);
               System.err.println("operationId u==>"+operationId);
               System.err.println("OperationResourceIdId u==>"+OperationResourceIdId);
               System.err.println("rscId u==>"+rscId);
                ls=(ArrayList) invokeOkHttps.callPatchServices(workOrderId, operationId, OperationResourceIdId, mf.getPatchPayroll(details));
                 
                 if(ls.get(0).toString().equalsIgnoreCase("SUCCESS")){
                    String resourcesPkId=ls.get(1).toString();
                    System.err.println("resourcesId success==>"+resourcesPkId);//wo op resource id
                 }else{
                    String message=ls.get(1).toString();
                    System.err.println("resourcesId error==>"+message); 
                 }
                //===================                 
               resultstatus=CallDBQuery.updateStatus(ls.get(0).toString(), ls.get(1).toString(), seqHdrId, resId);
               
            // Update - end                
           } 
        
        }
        return Response.ok()
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                    .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").
                      entity(responseToRest(resultstatus)).build();

    }
    
 
        public String responseToRest(String result) throws JSONException {
            JSONObject json = new JSONObject();
            json.put("result", result);
            return json.toString();
        }
    
    
}
