package com.ssmshiro.service;

import org.apache.shiro.authz.annotation.RequiresRoles;

public class ShiroService {

	@RequiresRoles({"admin"})
	public void testAnnotationShiro(){
		System.out.println("拥有admin权限的方法");
	}
}
