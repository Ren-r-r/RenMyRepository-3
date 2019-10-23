package com.ryz.MyBatis.Mapper;
/**
 * emp映射接口
 * @author Administrator
 *
 */

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ryz.MyBatis.entity.emp;

public interface empMapper {

	//查询的方法
	public emp getemp(int empno);
	
	//根据empno,ename查询单个员工
	public emp getempandname(@Param("empno")int empno,@Param("ename")String ename);
	
	//查询所有员工
	public List<emp> getallemp();
	
	//根据地址模糊查询
	public List<emp> getemplike(String Address);
	
	//添加员工
	public int insertemp(emp emp);
	
	//删除员工
	public int deleteemp(Integer empno);
	
	//修改员工
	public int updateemp(emp emp);
	
	//模糊查询(动态sql)
	public List<emp> likeemp(emp emp);
	
	//修改(动态sql)
	public Boolean updaemp(emp emp);
	
	//choose满足一个条件后面都不管(动态sql)
	public List<emp> choose(emp emp);
	
	//foreach(动态sql)
	public List<emp> foremp(@Param("list")List<Integer> list);
	
	//批量添加员工(第一种写法)
	public int inerempBath(@Param("emps")List<emp> emps);
	
	//批量添加员工(第二种写法)
	public int inerempBaths(@Param("emps")List<emp> emps);
	
	//使用sql语句分页
	@Select("select * from emp limit #{page},#{pagesize}")
	public List<emp> listpage(Map<String,Integer> pager);
}
