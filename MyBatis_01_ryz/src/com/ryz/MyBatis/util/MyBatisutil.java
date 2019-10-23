package com.ryz.MyBatis.util;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 工具类(生产session)
 * @author Administrator
 *
 */
public class MyBatisutil {

	SqlSessionFactory factory=null;
	
	//1.创建构造私有化
	private MyBatisutil () {
		try {
			factory=new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("MyBatis-conf.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//2.属性私有,实例化对象
	private static MyBatisutil instance=new MyBatisutil();
	
	//3.暴露一个公共方法,给别人调用
	public static SqlSession opensession() {
		return instance.factory.openSession();
	}
	
}
