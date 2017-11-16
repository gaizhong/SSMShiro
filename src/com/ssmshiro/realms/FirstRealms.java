package com.ssmshiro.realms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssmshiro.dao.UserDao;
import com.ssmshiro.daoImpl.UserDaoImpl;
import com.ssmshiro.userService.UserService;
import com.ssmshiro.userServiceImpl.UserServiceImpl;

public class FirstRealms extends AuthorizingRealm {
	@Autowired
	UserServiceImpl userservice;
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("[FirstRealm] doGetAuthenticationInfo");
		UsernamePasswordToken uptoken=(UsernamePasswordToken)token;
		String username=uptoken.getUsername();
		String password=String.valueOf(uptoken.getPassword());
		Object principal=username;
		List list = userservice.queryUser(null);
		System.out.println(list);
		//fc1709d0a95a6be30bc5926fdb7f22f4   123456经过 MD5加密后1024次后
//		Object credentials="123456";
		Object credentials="fc1709d0a95a6be30bc5926fdb7f22f4";
		String realmName=getName();
		if("admin".equals(username)){
			credentials="038bdaf98f2037b31f1e75b5b4c9b26e";
		}else if("user".equals(username)){
			credentials="098d2c478e9c11555ce2823231e02ec1";
		}
		//4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
		if("unknown".equals(username)){
			throw new UnknownAccountException("用户不存在!");
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

	@Override//授权的时候的处理
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principle) {
		System.out.println("授权操作doGetAuthorizationInfo......");
		//获取用户登录信息
		Object object = principle.getPrimaryPrincipal();
		Set<String> roles=new HashSet<String>();
		roles.add("user");
		if("admin".equals(object)){
			roles.add("admin");
		}
		SimpleAuthorizationInfo sainfo=new SimpleAuthorizationInfo(roles);
		return sainfo;
	}
	
}
