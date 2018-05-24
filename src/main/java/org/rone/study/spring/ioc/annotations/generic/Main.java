package org.rone.study.spring.ioc.annotations.generic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public class Main {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("/ioc-annotations-generic.xml");
		BaseService<User> baseService = (BaseService<User>) context.getBean("baseService");
		/*
		 * 泛型依赖注入
		 * 泛型指定为 User 后，容器会根据泛型来装配相应的Bean
		 */
		baseService.save();
		context.close();
		/* 输出：
		 BaseService save..
		 BaseDao save..
		 UserDao save..
		 */
	}
}
@Service
class BaseService<T> {
	@Autowired
	public BaseDao<T> baseDao;
	
	public void save() {
		System.out.println("BaseService save..");
		baseDao.save();
	}
}

class BaseDao<T> {
	public void save() {System.out.println("BaseDao save..");}
}

@Repository
class UserDao extends BaseDao<User> {
	@Override
	public void save() {
		super.save();
		System.out.println("UserDao save..");
	}
}

class User {}

