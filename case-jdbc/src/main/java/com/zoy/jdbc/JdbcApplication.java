package com.zoy.jdbc;

public class JdbcApplication {

	public static void main(String[] args) {

		// 新增
		/*Student student=new Student();
		student.setName("张三");
		student.setAge(18);
		student.setSex(1);
		int result=StudentDao.insert(student);
		System.out.println("插入成功！条数："+result);*/

		// 修改
		/*Student student=new Student();
		student.setName("张四");
		student.setAge(18);
		student.setSex(1);
		int result=StudentDao.update(student);
		System.out.println("修改成功！条数："+result);*/

		// 删除
		/*Student student=new Student();
		student.setName("张四");
		student.setAge(18);
		student.setSex(1);
		int result=StudentDao.delete(student);
		System.out.println("删除成功！条数："+result);*/

		// 查询
		Student student=new Student();
		student.setName("张四");
		student.setAge(18);
		student.setSex(1);
		int result=StudentDao.insert(student);
		System.out.println("查询成功！条数："+result);
	}

}
