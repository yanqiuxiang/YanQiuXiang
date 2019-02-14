package com.yqx.jurisdiction.business;

import java.util.List;

import com.yqx.jurisdiction.entity.Users;


public interface UserMapper {
	
	public Users login(String userName,String passWord);
	
	public void updLoginTime(int user_id);
	
	public List<Users> getAllUser();
	
	//�޸��û�״̬
	public void updUserStatus(int USER_ID,int USER_STATUS);
	
	//�����û���Ϣ
	public void addUser(String USER_ACCOUNT,String USER_PASSWORD,String USER_NAME,int ROLE_ID,
			String userExplain,String USER_MAIL,long USER_PHONE,long addRealName);

	//��ȡĳһ�û���Ϣ
	public Users getUserById(int userId);
	
	//�޸��û���ɫ
	public void updUserByRole(int USER_ID,int ROLE_ID,String USER_ROLE,String USER_NAME,
			String USER_MAIL,long USER_PHONE,long addRealName);
	
	//ɾ���û���Ϣ
	public void delUser(int USER_ID);
	
	//�޸�����
	public void updPassword(Integer userid,String uPassword);
	
	//ͨ���û�����ȡ�û�����
	public String getMailByUserName(String userName);
	
	//��ѯ�Ƿ�����ͬ���û��˺�
	public int getCountUserByName(String userName);
	
	public List<Users> getUserAll();
}
