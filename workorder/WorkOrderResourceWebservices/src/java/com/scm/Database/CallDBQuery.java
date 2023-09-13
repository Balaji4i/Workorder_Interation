/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scm.Database;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import static jdk.nashorn.internal.objects.NativeArray.map;
/**
 *
 * @author DineshkumarP
 */
public class CallDBQuery {

    static Connection connection;
    static Statement statement;
    static ResultSet resultSet;
    static PreparedStatement ps;
    
    /**
     * Method to initialize DB connection
     * @throws SQLException
     * @throws NamingException
     * @throws ClassNotFoundException 
     */

    public static void dbInitialization() throws SQLException, NamingException, ClassNotFoundException {
//        connection = DBConnection.getConnectionDS("subcont");    //CLOUD
        connection = DBConnection.getDBConnection();                //LOCAL
    }

    /**
     * Method to destroy DB connection
     * @throws SQLException 
     */
    public static void dbDestroy() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    
    public static Statement getStatement() throws SQLException{
        return connection.createStatement();
    }
    
    
    public static HashMap<String, String> getTransferWorkOrder(){
            HashMap<String, String> map = new HashMap<>();        
        try {
            String primaryKeyId="0";
            int iVal = 0;
            //******************************            
            String SQL=
                "SELECT \n" +
                "WO_SCH_MAIN_HDR_ID, INTERFACE_STATUS, RESOURCETABLEID, WOOPERATIONID, "
            +   "WORKORDERID, BASISTYPE, RESOURCECODE, REQUIREDUSAGE, RESOURCESEQUENCENUMBER, "
            +   "USAGERATE, PLANNEDSTARTDATE, PLANNEDCOMPLETIONDATE, WOOPERATIONRESOURCEID, PAYLOAD_TYPE\n" +
                "FROM \n" +
                "XXPL_WORK_ORDER_TRANSFER_V\n" +
                "WHERE ROWNUM=1";
                    
//            System.out.println("SQL==>"+SQL);
            
            dbInitialization();
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
//            System.out.println("resultSet==>"+resultSet);
            if(resultSet!=null){
                while (resultSet.next()){
                    primaryKeyId=resultSet.getString("WO_SCH_MAIN_HDR_ID")==null?"0":resultSet.getString("WO_SCH_MAIN_HDR_ID").toString();
//                    System.out.println("primaryKeyId==>"+primaryKeyId);
                    if(primaryKeyId.equalsIgnoreCase("0")){
                        map.put("HdrId"                 , resultSet.getString("WO_SCH_MAIN_HDR_ID"));
                    }else{
                        map.put("HdrId"                 , resultSet.getString("WO_SCH_MAIN_HDR_ID"));
                        map.put("ResourceTableId"       , resultSet.getString("RESOURCETABLEID"));
                        map.put("OperationId"           , resultSet.getString("WOOPERATIONID"));
                        map.put("WorkOrderId"           , resultSet.getString("WORKORDERID"));
                        map.put("BasisType"             , resultSet.getString("BASISTYPE"));
                        map.put("ResourceCode"          , resultSet.getString("RESOURCECODE"));
                        map.put("RequiredUsage"         , resultSet.getString("REQUIREDUSAGE"));
                        map.put("ResourceSequenceNumber", resultSet.getString("RESOURCESEQUENCENUMBER")==null?"0":resultSet.getString("RESOURCESEQUENCENUMBER"));
                        map.put("UsageRate"             , resultSet.getString("USAGERATE"));
                        map.put("PlannedStartDate"      , resultSet.getString("PLANNEDSTARTDATE"));
                        map.put("PlannedCompletionDate" , resultSet.getString("PLANNEDCOMPLETIONDATE"));
                        map.put("OperationResourceId"   , resultSet.getString("WOOPERATIONRESOURCEID"));
                        map.put("PayloadType"           , resultSet.getString("PAYLOAD_TYPE"));
                        map.put("ResourceId"            , resultSet.getString("WOOPERATIONRESOURCEID"));
                    
                    
                    }
                }
            }else{
                  map.put("HdrId", "0");
//                System.out.println("=EE==>");
            }
            dbDestroy();            
            return map;
        } catch (SQLException ex) {
            map.put("HdrId", "0");
            Logger.getLogger(CallDBQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            map.put("HdrId", "0");    
            Logger.getLogger(CallDBQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            map.put("HdrId", "0");    
            Logger.getLogger(CallDBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
       return map; 
    }
    
    
    public static HashMap<String, String> getTransferWorkOrderById(String resourceId){
            HashMap<String, String> map = new HashMap<>();        
        try {
            String primaryKeyId="0";
            int iVal = 0;
            //******************************            
            String SQL=
                "SELECT \n" +
                "WO_SCH_MAIN_HDR_ID, INTERFACE_STATUS, RESOURCETABLEID, WOOPERATIONID, "
            +   "WORKORDERID, BASISTYPE, RESOURCECODE, REQUIREDUSAGE, RESOURCESEQUENCENUMBER, "
            +   "USAGERATE, PLANNEDSTARTDATE, PLANNEDCOMPLETIONDATE, WOOPERATIONRESOURCEID, PAYLOAD_TYPE,RESOURCEID\n" +
                "FROM \n" +
                "XXPL_WORK_ORDER_TRANS_HDR_ID_V\n" +
                "WHERE RESOURCETABLEID="+resourceId;
                    
//            System.out.println("SQL==>"+SQL);
            
            dbInitialization();
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
//            System.out.println("resultSet==>"+resultSet);
            if(resultSet!=null){
                while (resultSet.next()){
                    primaryKeyId=resultSet.getString("WO_SCH_MAIN_HDR_ID")==null?"0":resultSet.getString("WO_SCH_MAIN_HDR_ID").toString();
//                    System.out.println("primaryKeyId==>"+primaryKeyId);
                    if(primaryKeyId.equalsIgnoreCase("0")){
                        map.put("HdrId"                 , resultSet.getString("WO_SCH_MAIN_HDR_ID"));
                    }else{
                        map.put("HdrId"                 , resultSet.getString("WO_SCH_MAIN_HDR_ID"));
                        map.put("ResourceTableId"       , resultSet.getString("RESOURCETABLEID"));
                        map.put("OperationId"           , resultSet.getString("WOOPERATIONID"));
                        map.put("WorkOrderId"           , resultSet.getString("WORKORDERID"));
                        map.put("BasisType"             , resultSet.getString("BASISTYPE"));
                        map.put("ResourceCode"          , resultSet.getString("RESOURCECODE"));
                        map.put("RequiredUsage"         , resultSet.getString("REQUIREDUSAGE"));
                        map.put("ResourceSequenceNumber", resultSet.getString("RESOURCESEQUENCENUMBER")==null?"0":resultSet.getString("RESOURCESEQUENCENUMBER"));
                        map.put("UsageRate"             , resultSet.getString("USAGERATE"));
                        map.put("PlannedStartDate"      , resultSet.getString("PLANNEDSTARTDATE"));
                        map.put("PlannedCompletionDate" , resultSet.getString("PLANNEDCOMPLETIONDATE"));
                        map.put("OperationResourceId"   , resultSet.getString("WOOPERATIONRESOURCEID"));
                        map.put("PayloadType"           , resultSet.getString("PAYLOAD_TYPE"));
                        map.put("ResourceId"            , resultSet.getString("RESOURCEID"));
                    }
                }
            }else{
                  map.put("HdrId", "0");
//                System.out.println("=EE==>");
            }
            dbDestroy();            
            return map;
        } catch (SQLException ex) {
            map.put("HdrId", "0");
            Logger.getLogger(CallDBQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            map.put("HdrId", "0");    
            Logger.getLogger(CallDBQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            map.put("HdrId", "0");    
            Logger.getLogger(CallDBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
       return map; 
    }
    
    
    
    
    
    
    
                        
        public static String updateStatus(
                                        String p_TYPE, 
                                        String p_MESSAGE,
                                        String p_WO_SCH_MAIN_HDR_ID,
                                        String p_RESOURCE_ID
                                        ) throws SQLException, NamingException, ClassNotFoundException{
        String result="N";
        dbInitialization();
        String pkgName="XXPM_WO_UPDATE_STATUS";
            CallableStatement cst= connection.prepareCall("{call "+pkgName+"(?,?,?,?,?)}");
        cst.setString(1, p_TYPE);
        cst.setString(2, p_MESSAGE);
        cst.setString(3, p_WO_SCH_MAIN_HDR_ID);
        cst.setString(4, p_RESOURCE_ID);       
        cst.registerOutParameter(5, Types.VARCHAR);
        cst.execute();
        result=cst.getString(5);
        dbDestroy();
        System.err.println("Pkg result==>"+result);
        return result;
        
    }    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
