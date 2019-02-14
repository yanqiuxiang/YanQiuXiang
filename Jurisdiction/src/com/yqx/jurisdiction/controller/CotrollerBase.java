package com.yqx.jurisdiction.controller;

import javax.annotation.Resource;

public class CotrollerBase {
	
	protected BusinessFactory factory;
	
	@Resource
	public void setFactory(BusinessFactory factory) {
		this.factory = factory;
	}
	
	

}
