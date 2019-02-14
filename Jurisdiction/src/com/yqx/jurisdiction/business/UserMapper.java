package com.yqx.jurisdiction.business;

import java.util.List;

import com.yqx.jurisdiction.entity.Users;


public interface UserMapper {
	
	public Users login(String userName,String passWord);
	
	public void updLoginTime(int user_id);
	
	public List<Users> getAllUser();
	
	//修改用户状态
	public void updUserStatus(int USER_ID,int USER_STATUS);
	
	//新增用户信息
	public void addUser(String USER_ACCOUNT,String USER_PASSWORD,String USER_NAME,int ROLE_ID,
			String userExplain,String USER_MAIL,long USER_PHONE,long addRealName);

	//获取某一用户信息
	public Users getUserById(int userId);
	
	//修改用户角色
	public void updUserByRole(int USER_ID,int ROLE_ID,String USER_ROLE,String USER_NAME,
			String USER_MAIL,long USER_PHONE,long addRealName);
	
	//删除用户信息
	public void delUser(int USER_ID);
	
	//修改密码
	public void updPassword(Integer userid,String uPassword);
	
	//通过用户名获取用户邮箱
	public String getMailByUserName(String userName);
	
	//查询是否有相同的用户账号
	public int getCountUserByName(String userName);
	
	public List<Users> getUserAll();
}
