package com.ssmshiro.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.ByteSource;

public class SecondShiroRealm extends AuthenticatingRealm{

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("[SecondRealm] doGetAuthenticationInfo");
		System.out.println("doGetAuthenticationInfo:"+token);
		//1把AuthenticationToken转换成 UsernamePasswordToken
		UsernamePasswordToken uptoken=(UsernamePasswordToken)token; 
		//2从UsernamePasswordToken中来获取username
		String username=uptoken.getUsername();
		String password=String.valueOf(uptoken.getPassword());
		//3调用数据库查询username对应的用户
		System.out.println("数据库中username:"+username);
		System.out.println("数据库中password:"+password);
		//4 用户不存在跑出UnknowAccountException异常
		if("unknow".equals(username)){
			throw new UnknownAccountException("用户不存在");
		}
		//5根据用户信息的情况是否抛出其他异常
		if("monster".equals(username)){
			throw new LockedAccountException("用户被锁");
		}
		//6根据用户情况，构建AuthenticationInfo 对象并返回
		//以下信息时从数据库获取
		//principal:认证信息，可以是username，也可以是用户表对应实体类对象
		Object principal=username;
		//fc1709d0a95a6be30bc5926fdb7f22f4   123456经过 MD5加密后1024次后
//		Object credentials="123456";
		Object credentials="fc1709d0a95a6be30bc5926fdb7f22f4";
		String realmName=getName();
		if("admin".equals(username)){
			credentials="ce2f6417c7e1d32c1d81a797ee0b499f87c5de06";
		}else if("user".equals(username)){
			credentials="073d4c3ae812935f23cb3f2a71943f49e082a718";
		}
		ByteSource credentialsSalt=ByteSource.Util.bytes(username);
		SimpleAuthenticationInfo info=new 
//				SimpleAuthenticationInfo(principal, credentials, realmName);
			SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName)	;
		return info;
	}

	public static void main(String[] args) {
		SimpleHash simpleHash = new SimpleHash("SHA1", "123456", "admin", 1024);
		System.out.println(simpleHash);
	}

	
}
