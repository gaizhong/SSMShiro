package com.ssmshiro.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssmshiro.service.ShiroService;


@Controller
@RequestMapping("/shiro")
public class LoginController {
//	@Autowired(required=true)
//	
//	@Autowired(required=true)
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response){
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		System.out.println(username);
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken putoken=new UsernamePasswordToken(username, password);
		if(!subject.isAuthenticated()){
			try {
				subject.login(putoken);
			} catch (AuthenticationException e) {
				System.out.println("认证失败。。。。。。");
				e.getMessage();
			}
		}
		return "redirect:/list.jsp";
	}
	@Autowired
	ShiroService shiroService;
	@RequestMapping("/testAnnotation")
	public String testAnnotation(){
		shiroService.testAnnotationShiro();
		return "redirect:/list.jsp";
	}
}
