package com.yqx.jurisdiction.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.yqx.jurisdiction.entity.Users;


public interface UserDao {
	
	public Users login(@Param("userName")String userName,@Param("passWord")String passWord);
	
	public void updLoginTime(@Param("user_id")int user_id);
	
	public List<Users> getAllUser();
	
	//修改用户状态
	public void updUserStatus(@Param("USER_ID")int USER_ID,@Param("USER_STATUS")int USER_STATUS);
	
	//新增用户信息
	public void addUser(@Param("USER_ACCOUNT")String USER_ACCOUNT,@Param("USER_PASSWORD")String USER_PASSWORD,
			@Param("USER_NAME")String USER_NAME,@Param("ROLE_ID")int ROLE_ID,
			@Param("userExplain")String userExplain,@Param("USER_MAIL")String USER_MAIL,
			@Param("USER_PHONE")long USER_PHONE,@Param("addRealName")long addRealName);

	//获取某一用户信息
	public Users getUserById(@Param("userId")int userId);
	
	//修改用户角色
	public void updUserByRole(@Param("USER_ID")int USER_ID,@Param("ROLE_ID")int ROLE_ID,@Param("USER_ROLE")String USER_ROLE,
			@Param("USER_NAME")String USER_NAME,@Param("USER_MAIL")String USER_MAIL,@Param("USER_PHONE")long USER_PHONE,
			@Param("addRealName")long addRealName);
	
	//删除用户信息
	public void delUser(@Param("USER_ID")int USER_ID);
	
	//修改密码
	public void updPassword(@Param("userid")Integer userid,@Param("uPassword")String uPassword);
	
	//通过用户名获取用户邮箱
	public String getMailByUserName(@Param("userName")String userName);
	
	//查询是否有相同的用户账号
	public int getCountUserByName(@Param("userName")String userName);
	
	
	//获取所有用户信息
	public List<Users> getUserAll();
}
