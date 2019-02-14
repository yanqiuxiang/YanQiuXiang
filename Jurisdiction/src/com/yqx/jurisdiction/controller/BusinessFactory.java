package com.yqx.jurisdiction.controller;


import java.util.Map;

public class BusinessFactory {
	private Map<String, Object> businesses;
	
	public BusinessFactory(Map<String, Object> businesses){
		this.businesses = businesses;
	}
	
	public Object getBusinessProduct(String businessName){
		return businesses.get(businessName);
	}
	
}
