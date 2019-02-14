package com.yqx.jurisdiction.entity;

import java.util.Date;

public class Users {
	
	private int USER_ID;//用户ID
	private String USER_ACCOUNT;//帐号
	private String USER_PASSWORD;//密码
	private int ROLE_STATUS;//角色状态
	private String USER_ROLE;//角色说明
	private Date USER_CREATDATE;//注册时间
	private Date USER_LASTDATE;//最后登录时间
	private int USER_STATUS;//状态（1－未审；2－锁定；3－审核）
	private int ROLE_ID;//角色编号
	private String USER_MAIL;//邮箱
	private String USER_NAME;
	private long USER_PHONE;//手机号码
	private int PERMISSION_ONE;//权限1
	private int PERMISSION_TWO; //权限2
	private int USER_CREATE_STATUS;
	private String STOREFRONT_PERMISSION;
	private String OPENID;
	private Integer DATA_RIGHT1;
	
	
	
	public void setDATA_RIGHT1(Integer dATA_RIGHT1) {
		DATA_RIGHT1 = dATA_RIGHT1;
	}
	public Integer getDATA_RIGHT1() {
		return DATA_RIGHT1;
	}
	public String getOPENID() {
		return OPENID;
	}
	public void setOPENID(String oPENID) {
		OPENID = oPENID;
	}
	public String getSTOREFRONT_PERMISSION() {
		return STOREFRONT_PERMISSION;
	}
	public void setSTOREFRONT_PERMISSION(String sTOREFRONT_PERMISSION) {
		STOREFRONT_PERMISSION = sTOREFRONT_PERMISSION;
	}
	public int getUSER_CREATE_STATUS() {
		return USER_CREATE_STATUS;
	}
	public void setUSER_CREATE_STATUS(int uSER_CREATE_STATUS) {
		USER_CREATE_STATUS = uSER_CREATE_STATUS;
	}
	public int getUSER_ID() {
		return USER_ID;
	}
	public String getUSER_ACCOUNT() {
		return USER_ACCOUNT;
	}
	public String getUSER_PASSWORD() {
		return USER_PASSWORD;
	}
	public int getROLE_STATUS() {
		return ROLE_STATUS;
	}
	public String getUSER_ROLE() {
		return USER_ROLE;
	}
	public Date getUSER_CREATDATE() {
		return USER_CREATDATE;
	}
	public Date getUSER_LASTDATE() {
		return USER_LASTDATE;
	}
	public int getUSER_STATUS() {
		return USER_STATUS;
	}
	public int getROLE_ID() {
		return ROLE_ID;
	}
	public String getUSER_MAIL() {
		return USER_MAIL;
	}
	public String getUSER_NAME() {
		return USER_NAME;
	}
	public long getUSER_PHONE() {
		return USER_PHONE;
	}
	public int getPERMISSION_ONE() {
		return PERMISSION_ONE;
	}
	public int getPERMISSION_TWO() {
		return PERMISSION_TWO;
	}
	public void setUSER_ID(int uSER_ID) {
		USER_ID = uSER_ID;
	}
	public void setUSER_ACCOUNT(String uSER_ACCOUNT) {
		USER_ACCOUNT = uSER_ACCOUNT;
	}
	public void setUSER_PASSWORD(String uSER_PASSWORD) {
		USER_PASSWORD = uSER_PASSWORD;
	}
	public void setROLE_STATUS(int rOLE_STATUS) {
		ROLE_STATUS = rOLE_STATUS;
	}
	public void setUSER_ROLE(String uSER_ROLE) {
		USER_ROLE = uSER_ROLE;
	}
	public void setUSER_CREATDATE(Date uSER_CREATDATE) {
		USER_CREATDATE = uSER_CREATDATE;
	}
	public void setUSER_LASTDATE(Date uSER_LASTDATE) {
		USER_LASTDATE = uSER_LASTDATE;
	}
	public void setUSER_STATUS(int uSER_STATUS) {
		USER_STATUS = uSER_STATUS;
	}
	public void setROLE_ID(int rOLE_ID) {
		ROLE_ID = rOLE_ID;
	}
	public void setUSER_MAIL(String uSER_MAIL) {
		USER_MAIL = uSER_MAIL;
	}
	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}
	public void setUSER_PHONE(long uSER_PHONE) {
		USER_PHONE = uSER_PHONE;
	}
	public void setPERMISSION_ONE(int pERMISSION_ONE) {
		PERMISSION_ONE = pERMISSION_ONE;
	}
	public void setPERMISSION_TWO(int pERMISSION_TWO) {
		PERMISSION_TWO = pERMISSION_TWO;
	}
}
