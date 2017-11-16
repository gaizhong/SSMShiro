package com.ssmshiro.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.ssmshiro.dao.UserDao;
@Repository
public class UserDaoImpl implements UserDao{
	@Resource(name="sqlSessionFactory")
	SqlSessionFactory sqlSessionFactory;
	@Override
	public List<Map<String, String>> queryUser(Map map) {
		 SqlSession session = sqlSessionFactory.openSession();
//		return session.selectOne("mybatis.mybatis.querybyname", map);
		 System.out.println("-------daoimpl-------"+session);
		List list=session.selectList("mybatis.querybyname");
//		session.selectOne("mybatisss.querybyname");
		System.out.println("list--->"+list);
		return list;
	}
	

	
}
