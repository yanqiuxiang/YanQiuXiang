DELIMITER $$


DROP PROCEDURE IF EXISTS `DELROLE_CALL`$$

CREATE PROCEDURE `DELROLE_CALL`(IN roleId INT,OUT v_msg  VARCHAR(20))
BEGIN
    
	DELETE FROM  ROLE WHERE ROLE_ID = roleId;
        DELETE FROM  ROLE_NODE WHERE ROLE_ID =roleId;
	SET v_msg='删除成功';
    END$$

DELIMITER ;