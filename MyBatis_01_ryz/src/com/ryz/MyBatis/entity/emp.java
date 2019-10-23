package com.ryz.MyBatis.entity;
/**
 * 员工表实体类
 * @author Administrator
 *
 */
public class emp {
	
	private Integer empno;
	private String ename;
	private float esal;
	private String eAddress;
	
	public Integer getEmpno() {
		return empno;
	}
	public void setEmpno(Integer empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public float getEsal() {
		return esal;
	}
	public void setEsal(float esal) {
		this.esal = esal;
	}
	public String geteAddress() {
		return eAddress;
	}
	public void seteAddress(String eAddress) {
		this.eAddress = eAddress;
	}
	@Override
	public String toString() {
		return "emp [empno=" + empno + ", ename=" + ename + ", esal=" + esal + ", eAddress=" + eAddress + "]";
	}
	
	public emp( String ename, float esal, String eAddress) {
		super();
		this.ename = ename;
		this.esal = esal;
		this.eAddress = eAddress;
	}
	
	public emp(Integer empno,String ename, float esal, String eAddress) {
		super();
		this.empno=empno;
		this.ename = ename;
		this.esal = esal;
		this.eAddress = eAddress;
	}

	public emp() {}
	
	
	
}
