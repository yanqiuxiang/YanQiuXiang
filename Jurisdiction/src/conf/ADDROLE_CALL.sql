DELIMITER $$

USE `Jurisdiction`$$

DROP PROCEDURE IF EXISTS `ADDROLE_CALL`$$

CREATE DEFINER=`XT_RN`@`%` PROCEDURE `ADDROLE_CALL`(IN nodeIds VARCHAR(4000) ,IN role_name VARCHAR(300) ,IN role_access VARCHAR(4000),IN role_remark VARCHAR(4000),IN role_receive BIGINT,OUT v_msg VARCHAR(20))
BEGIN	
		
		DECLARE result_code INTEGER DEFAULT 0;
		DECLARE i INT;
		DECLARE str VARCHAR(20);
		DECLARE roleId BIGINT;
		DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET result_code=1; 
		START TRANSACTION; 
			
			SELECT (AUTO_INCREMENT) INTO roleId FROM INFORMATION_SCHEMA.TABLES   WHERE TABLE_NAME='ROLE' LIMIT 0,1;
			INSERT INTO ROLE(ROLE_ID,ROLE_NAME,ROLE_ACCESS,ROLE_STATUS,ROLE_REMARK,PERMISSION_ONE) VALUES(roleId,role_name,role_access,0,role_remark,role_receive);
			SET i=1;
			myloop:LOOP 
				SET str=SPLIT(nodeIds,',', i);
				IF str = '' THEN 
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