package com.yqx.jurisdiction.entity;

public class Role {
	
	private Long ROLE_ID;
	private String ROLE_NAME;
	private String ROLE_ACCESS;
	private Integer ROLE_STATUS;
	private Long PERMISSION_ONE;
	private Long PERMISSION_TWO;
	private String ROLE_REMARK;
	
	
	public Long getROLE_ID() {
		return ROLE_ID;
	}
	public String getROLE_NAME() {
		return ROLE_NAME;
	}
	public String getROLE_ACCESS() {
		return ROLE_ACCESS;
	}
	public Integer getROLE_STATUS() {
		return ROLE_STATUS;
	}
	public Long getPERMISSION_ONE() {
		return PERMISSION_ONE;
	}
	public Long getPERMISSION_TWO() {
		return PERMISSION_TWO;
	}
	public String getROLE_REMARK() {
		return ROLE_REMARK;
	}
	public void setROLE_ID(Long rOLE_ID) {
		ROLE_ID = rOLE_ID;
	}
	public void setROLE_NAME(String rOLE_NAME) {
		ROLE_NAME = rOLE_NAME;
	}
	public void setROLE_ACCESS(String rOLE_ACCESS) {
		ROLE_ACCESS = rOLE_ACCESS;
	}
	public void setROLE_STATUS(Integer rOLE_STATUS) {
		ROLE_STATUS = rOLE_STATUS;
	}
	public void setPERMISSION_ONE(Long pERMISSION_ONE) {
		PERMISSION_ONE = pERMISSION_ONE;
	}
	public void setPERMISSION_TWO(Long pERMISSION_TWO) {
		PERMISSION_TWO = pERMISSION_TWO;
	}
	public void setROLE_REMARK(String rOLE_REMARK) {
		ROLE_REMARK = rOLE_REMARK;
	}
}
