package org.rone.study.spring.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class Main {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/application-jdbc.xml");
		String sql = null;
		JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		
		//JdbcTemplate 查询单个结果
		sql = "SELECT * FROM employee WHERE id = ?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		Employee employee = jdbcTemplate.queryForObject(sql, rowMapper, 1);
		System.out.println(employee);
		
		//JdbcTemplate 多个结果的查询
//		sql = "SELECT * FROM employee";
//		List<Employee> employeeList = jdbcTemplate.query(sql, rowMapper);
//		System.out.println(employeeList);
		
		//JdbcTemplate 单个SQL语句的增删改
//		sql = "UPDATE employee SET name = ? WHERE id = ?";
//		jdbcTemplate.update(sql, "Jack", 1);
		
		//JdbcTemplate 批量操作SQL语句
//		sql = "INSERT INTO employee(id,name,email) VALUES(?,?,?)";
//		List<Object[]> objList = new ArrayList<Object[]>(2);
//		objList.add(new Object[] {3, "Rose", "Rose@foxmail.com"});
//		objList.add(new Object[] {4, "Lucy", "Lucy@foxmail.com"});
//		jdbcTemplate.batchUpdate(sql, objList);
		
		//JdbcTemplate 查询单个属性或做统计
//		sql = "SELECT COUNT(1) FROM employee";
//		Long count = jdbcTemplate.queryForObject(sql, Long.class);
//		System.out.println("Count: " + count);
		
		//NamedParameterJdbcTemplate 可使用具名参数，便于代码维护
//		NamedParameterJdbcTemplate namedParameterJdbcTemplate = context.getBean(NamedParameterJdbcTemplate.class);
//		//namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);//也可以这样来获取具名参数模板
//		sql = "UPDATE employee SET name = :name WHERE id = :id";
//		Map<String, Object> paramMap = new HashMap<>(2);
//		paramMap.put("name", "King");
//		paramMap.put("id", 4);
//		namedParameterJdbcTemplate.update(sql, paramMap);
		
		//NamedParameterJdbcTemplate 当SQL中的参数名和POJO中的属性名一致时，可直接使用POJO进行SQL操作
//		sql = "INSERT INTO employee(name,email) VALUES(:name,:email)";
//		Employee newEmployee = new Employee("Queen", "Queen@foxmail.com");
//		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(newEmployee);
//		namedParameterJdbcTemplate.update(sql, paramSource);
		
		((AbstractApplicationContext)context).close();
	}

}

class Employee {
	
	private Integer id;
	private String name;
	private String email;
	
	public Employee() {}
	
	public Employee(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
	
}
