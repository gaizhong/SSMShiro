package com.ssmshiro.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.ByteSource;

public class FirstRealms_bak extends AuthenticatingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("[FirstRealm] doGetAuthenticationInfo");
		UsernamePasswordToken uptoken=(UsernamePasswordToken)token;
		String username=uptoken.getUsername();
		String password=String.valueOf(uptoken.getPassword());
		Object principal=username;
		//fc1709d0a95a6be30bc5926fdb7f22f4   123456经过 MD5加密后1024次后
//		Object credentials="123456";
		Object credentials="fc1709d0a95a6be30bc5926fdb7f22f4";
		String realmName=getName();
		if("admin".equals(username)){
			credentials="038bdaf98f2037b31f1e75b5b4c9b26e";
		}else if("user".equals(username)){
			credentials="098d2c478e9c11555ce2823231e02ec1";
		}
		ByteSource credentialsSalt=ByteSource.Util.bytes(username);
		SimpleAuthenticationInfo info=new 
//				SimpleAuthenticationInfo(principal, credentials, realmName);
			SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName)	;
		return info;
	}

	public static void main(String[] args) {
		//加密算法
		String algorithmName="MD5";
		String source="123456";
		String salt="user";
		//加密次数
		int hashIterations=1024;
		SimpleHash sh=new SimpleHash(algorithmName, source, salt, hashIterations);
	}
	
}
