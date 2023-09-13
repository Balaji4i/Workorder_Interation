/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scm.process;

import com.scm.Database.CallDBQuery;
import com.scm.main.MainFile;
import static com.scm.main.MainFile.getPostPayroll;
import com.scm.payload.invokeOkHttps;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import org.json.JSONException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author DineshkumarP
 */
public class Schedule implements Job{

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
//        System.err.println("===>RUN");
        
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
               
                try {
                    String workOrderId=details.get("WorkOrderId")==null?"0":details.get("WorkOrderId").toString();
                    String operationId=details.get("OperationId")==null?"0":details.get("OperationId").toString();
                    String resourceId=details.get("ResourceTableId")==null?"0":details.get("ResourceTableId").toString();
                    String seqHdrId=details.get("HdrId")==null?"0":details.get("HdrId").toString();
                    String jsonData=MainFile.getPostPayroll(details); 
                    ls=(ArrayList) invokeOkHttps.callPostServices(workOrderId, operationId, jsonData);
                    
                    if(ls.get(0).toString().equalsIgnoreCase("SUCCESS")){
                        String resourcesId=ls.get(1).toString();
                        System.err.println("resourcesId==>"+resourcesId);
                    }else{
                        String message=ls.get(1).toString();
                        System.err.println("resourcesId==>"+message);
                    }
                    //===================
                    CallDBQuery.updateStatus(ls.get(0).toString(), ls.get(1).toString(), seqHdrId, resourceId);
                } catch (SQLException ex) {
                    Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NamingException ex) {
                    Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JSONException ex) {
                    Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
                }
           }else{
                // Update                

           } 
        
        }
        
        
        
    }

    
    
}
