--------------------------------------------------------
--  File created - Monday-January-03-2022   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure XXPM_WO_UPDATE_STATUS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "XXPM"."XXPM_WO_UPDATE_STATUS" (
                                p_TYPE      IN  VARCHAR2,
                                p_MESSAGE   IN  VARCHAR2,
                                p_WO_SCH_MAIN_HDR_ID IN  VARCHAR2,
                                p_RESOURCE_ID   IN  VARCHAR2,
                                p_status    OUT VARCHAR2
                                ) 
AS 

l_count number:=0;
BEGIN

IF(UPPER(p_TYPE)=UPPER('ERROR')) THEN 
    --UPDATE ERROR
    UPDATE XXPM_SCHEDULER_RESOURCE
    SET ATTRIBUTE1='ERROR',
        ATTRIBUTE2=p_MESSAGE
    WHERE 
    SC_TNX_RESOURCE_ID=p_RESOURCE_ID;
    COMMIT;
    p_status:='Y';
ELSE
    --
    UPDATE XXPM_SCHEDULER_RESOURCE
    SET ATTRIBUTE1='SUCCESS',
        WOOPERATIONRESOURCEID=p_MESSAGE
    WHERE 
    SC_TNX_RESOURCE_ID=p_RESOURCE_ID;
    COMMIT;
    
--    
   BEGIN
    SELECT nvl(COUNT(*), 0) INTO l_count
    FROM XXPL_WORK_ORDER_TRANSFER_V 
    WHERE WO_SCH_MAIN_HDR_ID=p_WO_SCH_MAIN_HDR_ID;
   exception when others then 
   l_count:=0;
   END;
   
   if(l_count=0)then
    update XXPM_WO_SCHEDULING_MAIN_HDR
    set 
    INTERFACE_STATUS='COMPLETED'
    WHERE
    WO_SCH_MAIN_HDR_ID=p_WO_SCH_MAIN_HDR_ID;
    COMMIT;
   end if; 
    p_status:='Y';    
END IF;


EXCEPTION
WHEN OTHERS THEN
    p_status:='N';
END XXPM_WO_UPDATE_STATUS;

/
