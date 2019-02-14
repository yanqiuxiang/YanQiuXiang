package com.yqx.jurisdiction.business.impl;

import java.util.List;
import com.yqx.jurisdiction.business.UserMapper;
import com.yqx.jurisdiction.dao.UserDao;
import com.yqx.jurisdiction.entity.Users;


public class UserMapperImpl implements UserMapper{

		UserDao realNameUserDao;
		
		
		public UserMapperImpl(UserDao realNameUserDao){
			
			this.realNameUserDao = realNameUserDao;
		}
		
		public Users login(String userName, String passWord) {
			
			return realNameUserDao.login(userName, passWord);
		}

		public void updLoginTime(int user_id) {
			
			realNameUserDao.updLoginTime(user_id);
		}

		public List<Users> getAllUser() {
			
			return realNameUserDao.getAllUser();
		}

		public void updUserStatus(int USER_ID, int USER_STATUS) {
			
			realNameUserDao.updUserStatus(USER_ID, USER_STATUS);
		}

		public void addUser(String USER_ACCOUNT, String USER_PASSWORD,
				String USER_NAME, int ROLE_ID, String userExplain,
				String USER_MAIL, long USER_PHONE,long addRealName) {
			
			realNameUserDao.addUser(USER_ACCOUNT, USER_PASSWORD, USER_NAME, ROLE_ID, userExplain,
					USER_MAIL, USER_PHONE,addRealName);
			
		}

		public Users getUserById(int userId) {
			
			return realNameUserDao.getUserById(userId);
		}

		public void updUserByRole(int USER_ID, int ROLE_ID, String USER_ROLE,
				String USER_NAME, String USER_MAIL, long USER_PHONE,long addRealName) {
			
			realNameUserDao.updUserByRole(USER_ID, ROLE_ID, USER_ROLE, USER_NAME, USER_MAIL, USER_PHONE,addRealName);
		}

		public void delUser(int USER_ID) {
			
			realNameUserDao.delUser(USER_ID);
		}

		public void updPassword(Integer userid, String uPassword) {
			
			
			realNameUserDao.updPassword(userid, uPassword);
		}

		public String getMailByUserName(String userName) {
			
			return realNameUserDao.getMailByUserName(userName);
		}

		public int getCountUserByName(String userName) {
			
			return realNameUserDao.getCountUserByName(userName);
		}

		public List<Users> getUserAll() {
			
			return realNameUserDao.getUserAll();
		}

		
		
		
}
