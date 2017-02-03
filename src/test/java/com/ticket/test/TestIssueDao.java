package com.ticket.test;

import java.sql.SQLException;
import java.time.LocalDate;

//import com.ticket.dao.DepartmentDao;
import com.ticket.dao.IssueDao;
//import com.ticket.dao.UserDao;
import com.ticket.model.Department;
import com.ticket.model.Issue;
import com.ticket.model.User;

public class TestIssueDao {
	
	public static void main(String[] args) throws SQLException
	{
		IssueDao id=new IssueDao();
		Issue i=new Issue();
		User u=new User();
		Department d=new Department();
		
		//DepartmentDao dd=new DepartmentDao();
		
	
	u.setId(1);
//		u.setName("Rahul");
//		u.setPassword("123");
//		u.setEmailId("rahul@gmail.com");
//		UserDao ud=new UserDao();
//		ud.save(u);
	
//		d.setName("HR");
//		
//		dd.save(d);
//		
	d.setId(1);
		i.setUserId(u);
		i.setDepartmentId(d);
		i.setSubject("Hai");
		i.setDescription("Hey");
		i.setDateResolved(LocalDate.parse("2017-03-12"));
	
		System.out.println(d);
		id.save(i);
	}

}
