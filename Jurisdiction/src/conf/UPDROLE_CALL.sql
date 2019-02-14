DELIMITER $$


DROP PROCEDURE IF EXISTS `UPDROLE_CALL`$$

CREATE  PROCEDURE `UPDROLE_CALL`(IN nodeIds VARCHAR(4000),IN roleId BIGINT,IN roleName VARCHAR(300),IN roleAccess VARCHAR(4000),IN roleRemark VARCHAR(300),IN roleReceive BIGINT,OUT v_msg VARCHAR(20))
BEGIN
	
		DECLARE result_code INTEGER DEFAULT 0;
		DECLARE i INT;
		DECLARE str VARCHAR(20);
		DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET result_code=1; 
		START TRANSACTION; 
		
			UPDATE ROLE SET ROLE_NAME = roleName,ROLE_ACCESS=roleAccess ,ROLE_REMARK=roleRemark,PERMISSION_ONE=roleReceive WHERE ROLE_ID=roleId;
		        DELETE FROM ROLE_NODE WHERE ROLE_ID = roleId;
			SET i=1;
			myloop:LOOP 
				SET str=SPLIT(nodeIds,',', i);
				IF STRCMP(str,'')=0 THEN 
				
					LEAVE myloop; 
				END IF;
				
				INSERT INTO ROLE_NODE(ROLE_ID,NODE_ID) VALUES(roleId,CAST(str AS SIGNED));
				SET i=i+1;
			END LOOP myloop;
			
		IF result_code = 1 THEN 
		
			SET v_msg ='数据库导常';	
			ROLLBACK; 
		ELSE 
			SET v_msg ='ok';
			COMMIT; 
		END IF;
	
    END$$

DELIMITER ;