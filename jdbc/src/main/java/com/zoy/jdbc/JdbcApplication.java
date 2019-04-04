package com.zoy.jdbc;

public class JdbcApplication {

	public static void main(String[] args) {
		Student student=new Student();
		student.setName("张三");
		student.setAge(18);
		student.setSex(1);
		int result=StudentDao.insert(student);
		System.out.println("插入成功！条数："+result);
	}

}
