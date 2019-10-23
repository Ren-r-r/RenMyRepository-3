package com.ryz.MyBatis.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.ryz.MyBatis.Mapper.empMapper;
import com.ryz.MyBatis.entity.emp;
import com.ryz.MyBatis.util.MyBatisutil;

public class MyBatisTest {

	@Test
	public void getemp() {
		//第二种方式:接口方式(推荐:符合思路)
				SqlSession session=null;
				try {
					//先打开会话
					session =MyBatisutil.opensession();
					//创建empMapper对象
					//代理模式:(帮你实现了接口)
					empMapper mapper= session.getMapper(empMapper.class);
					//调用接口方法:impl帮你写好了
					emp emp= mapper.getemp(2);
					System.out.println(emp);
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					if(session!=null)session.close();
				}
	}

	@Test
	public void getempandname() {
		SqlSession session=null;
		try {
			session =MyBatisutil.opensession();
			empMapper mapper= session.getMapper(empMapper.class);
			emp emp=mapper.getempandname(2,"李四");
			System.out.println(emp);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)session.close();
		}
	}
	
	@Test
	public void getallemp() {
		SqlSession session=null;
		try {
			session=MyBatisutil.opensession();
			empMapper mapper=session.getMapper(empMapper.class);
			List<emp> listemp=mapper.getallemp();
			for (emp emp : listemp) {
				System.out.println(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)session.close();
		}
	}
	
	@Test
	public void getemplike() {
		SqlSession session=null;
		try {
			session=MyBatisutil.opensession();
			empMapper mapper=session.getMapper(empMapper.class);
			List<emp> listemp=mapper.getemplike("%长%");
			for (emp emp : listemp) {
				System.out.println(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)session.close();
		}
	}
	
	@Test
	public void insertemp() {
		SqlSession session=null;
		try {
			session=MyBatisutil.opensession();
			empMapper mapper=session.getMapper(empMapper.class);
			emp emp=new emp("老王",9000,"湖南");
			int row=mapper.insertemp(emp);
			System.out.println(row);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null)session.rollback();
		}finally {
			if(session!=null)session.close();
		}
	}
	
	@Test
	public void deleteemp() {
		SqlSession session=null;
		try {
			session=MyBatisutil.opensession();
			empMapper mapper=session.getMapper(empMapper.class);
			int row=mapper.deleteemp(1);
			System.out.println(row);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null)session.rollback();
		}finally {
			if(session!=null)session.close();
		}
	}
	
	@Test
	public void updateemp() {
		SqlSession session=null;
		try {
			session=MyBatisutil.opensession();
			empMapper mapper=session.getMapper(empMapper.class);
			emp emp=new emp(5,"张三",5000,"长沙");
			int row=mapper.updateemp(emp);
			System.out.println(row);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null)session.rollback();
		}finally {
			if(session!=null)session.close();
		}
	}
	
	@Test
	//动态sql(模糊查询)
	public void likeemp() {
		SqlSession session=null;
		try {
			session=MyBatisutil.opensession();
			empMapper mapper= session.getMapper(empMapper.class);
			emp emp=new emp(null,3500,"%深%");
			List<emp> result= mapper.likeemp(emp);
			for (emp emp2 : result) {
				System.out.println(emp2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)session.close();
		}
	}
	
	@Test
	//动态sql(修改)
	public void updaemp() {
		SqlSession session=null;
		try {
			session=MyBatisutil.opensession();
			empMapper mapper= session.getMapper(empMapper.class);
			emp emp=new emp(2,"老李1",2500,null);
			Boolean result= mapper.updaemp(emp);
			System.out.println(result);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(session!=null) session.rollback();
		}finally {
			if(session!=null)session.close();
		}
	}
	
	
	@Test//(choose动态sql)
	public void choose() {
		SqlSession session=null;
		try {
			session= MyBatisutil.opensession();
			empMapper mapper= session.getMapper(empMapper.class);
			emp emp=new emp(2,null,3500,null);
			List<emp> result= mapper.choose(emp);
			for (emp emp1 : result) {
				System.out.println(emp1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)session.close();
		}
	}
	
	@Test//(批量动态sql)
	public void foremp() {
		SqlSession session=null;
		try {
			session= MyBatisutil.opensession();
			empMapper mapper= session.getMapper(empMapper.class);
			List<Integer> list=new ArrayList<Integer>();
			list.add(3);
			list.add(6);
			list.add(8);
			List<emp> result= mapper.foremp(list);
			for (emp emp1 : result) {
				System.out.println(emp1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)session.close();
		}
	}
	
	//<!-- 批量添加员工(第一种写法) -->
	//<!-- public int inerempBath(@Param("emps")List<emp> emps); -->
	@Test
	public void inerempBath() {
		SqlSession session=null;
		try {
			session= MyBatisutil.opensession();
			empMapper mapper= session.getMapper(empMapper.class);
			List<emp> list=new ArrayList<emp>();
			list.add(new emp("德玛", 900,"德玛西亚之地"));
			list.add(new emp("洛手", 1900,"一碟"));
			list.add(new emp("vn", 800,"菜逼"));
			int result= mapper.inerempBath(list);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {session.commit();
			if(session!=null)session.close();
		}
	}
	
	//<!-- 批量添加员工(第二种写法) -->
		//<!-- public int inerempBath(@Param("emps")List<emp> emps); -->
		@Test
		public void inerempBaths() {
			SqlSession session=null;
			try {
				session= MyBatisutil.opensession();
				empMapper mapper= session.getMapper(empMapper.class);
				List<emp> list=new ArrayList<emp>();
				list.add(new emp("德玛", 900,"德玛西亚之地"));
				list.add(new emp("洛手", 1900,"一碟"));
				list.add(new emp("vn", 800,"菜逼"));
				int result= mapper.inerempBaths(list);
				System.out.println(result);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {session.commit();
				if(session!=null)session.close();
			}
		}
		
		
		@Test
		//使用sql语句分页
		public void listpage() {
			SqlSession session=null;
			int page=2;
			int pagesize=3;
			try {
				session=MyBatisutil.opensession();
				empMapper mapper= session.getMapper(empMapper.class);
				Map<String, Integer> pager=new HashMap<String, Integer>();
				pager.put("page",page);
				pager.put("pagesize",(page-1)*pagesize);
				List<emp> result= mapper.listpage(pager);
				for (emp emp2 : result) {
					System.out.println(emp2);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(session!=null)session.close();
			}
		}
}
