package org.rone.study.spring.ioc.annotations;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public class Main {
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("/ioc-annotations.xml");
		EmployeeController employeeController = (EmployeeController) context.getBean("employeeController");
		employeeController.execute();
		context.close();
	}
}

@Component("employee")
class Employee {

	//指定初始化方法，在实例化时就会初始化;
	@PostConstruct
	private void init() {
		System.out.println(Employee.class + " init.....");
	}

	//指定销毁方法;
	@PreDestroy
	private void destroy() {
		System.out.println(Employee.class + " destroy...........");
	}

	public void show() {System.out.println("Employee show..");}
}

@Controller
class EmployeeController {
	@Autowired
	private Employee employee;
	@Resource(name="employeeService")
	private EmployeeService employeeService;
	@Resource
	private EmployeeDao employeeDao;
	public void execute() {
		System.out.println("EmployeeController execute..");
		employee.show();
		employeeService.save();
		employeeDao.save();
	}
}

interface EmployeeDao {
	void save();
}

@Repository
class EmployeeDaoImpl implements EmployeeDao {
	@Override
	public void save() {System.out.println("EmployeeDaoImpl save..");}
}

@Service
class EmployeeService {
	public void save() {System.out.println("EmployeeService save..");}
}
