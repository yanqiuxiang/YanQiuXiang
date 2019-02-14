package com.yqx.jurisdiction.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.yqx.jurisdiction.entity.Users;


public interface UserDao {
	
	public Users login(@Param("userName")String userName,@Param("passWord")String passWord);
	
	public void updLoginTime(@Param("user_id")int user_id);
	
	public List<Users> getAllUser();
	
	//�޸��û�״̬
	public void updUserStatus(@Param("USER_ID")int USER_ID,@Param("USER_STATUS")int USER_STATUS);
	
	//�����û���Ϣ
	public void addUser(@Param("USER_ACCOUNT")String USER_ACCOUNT,@Param("USER_PASSWORD")String USER_PASSWORD,
			@Param("USER_NAME")String USER_NAME,@Param("ROLE_ID")int ROLE_ID,
			@Param("userExplain")String userExplain,@Param("USER_MAIL")String USER_MAIL,
			@Param("USER_PHONE")long USER_PHONE,@Param("addRealName")long addRealName);

	//��ȡĳһ�û���Ϣ
	public Users getUserById(@Param("userId")int userId);
	
	//�޸��û���ɫ
	public void updUserByRole(@Param("USER_ID")int USER_ID,@Param("ROLE_ID")int ROLE_ID,@Param("USER_ROLE")String USER_ROLE,
			@Param("USER_NAME")String USER_NAME,@Param("USER_MAIL")String USER_MAIL,@Param("USER_PHONE")long USER_PHONE,
			@Param("addRealName")long addRealName);
	
	//ɾ���û���Ϣ
	public void delUser(@Param("USER_ID")int USER_ID);
	
	//�޸�����
	public void updPassword(@Param("userid")Integer userid,@Param("uPassword")String uPassword);
	
	//ͨ���û�����ȡ�û�����
	public String getMailByUserName(@Param("userName")String userName);
	
	//��ѯ�Ƿ�����ͬ���û��˺�
	public int getCountUserByName(@Param("userName")String userName);
	
	
	//��ȡ�����û���Ϣ
	public List<Users> getUserAll();
}
