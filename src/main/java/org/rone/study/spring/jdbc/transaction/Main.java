package org.rone.study.spring.jdbc.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new  ClassPathXmlApplicationContext("/application-transaction.xml");
		
		StoreService storeService = context.getBean(StoreService.class);
		storeService.doSomething();
		
		((AbstractApplicationContext)context).close();
	}

}

@Service
class StoreService {
	
	@Autowired
	private StoreDao storeDao;

	/*
	 * 使用@Transactional 表明该方法开启事务
	 * 1.propagation 事务的传播，即在事务方法调用其他事务方法时，事务的工作模式。
	 * 	常用的两个事务传播行为：
	 * 		REQUIRED：默认项，公用同一个事务，即无论哪个子事务回滚，所有事务都回滚；
	 *  	REQUIRES_NEW：开启新的事务，若子事务回滚只回滚当前事务，该属性需要配置在子方法中，
	 *  					若子方法时通过throw Exception来触发事务回滚的话，该exception需要手动捕获，否则程序会在异常处终止执行；
	 * 2.isolation 事务的隔离级别，事务并发中的读取脏数据、不可重复读取、幻读；
	 * 3.noRollbackFor、noRollbackForClassName、rollbackFor、rollbackForClassName 
	 * 	来指定哪些异常不需要回滚和只回滚哪些异常；
	 * 4.readOnly(true/false) 指定事务是否为只读，便于帮助数据库引擎优化事务；
	 * 5.timeout(单位秒) 用来表明事务的最长执行时间，超时就会强制回滚；
	 */
//	@Transactional(propagation=Propagation.REQUIRED,
//			isolation=Isolation.READ_COMMITTED,
//			rollbackFor={Exception.class},
//			readOnly=true,
//			timeout=3)
	@Transactional
	public void doSomething() {
		storeDao.updateStore(1);
		storeDao.updateStore(2);
	}
	
}

@Repository
class StoreDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional
	public void updateStore(Integer id) {
		String sql = "SELECT count FROM store WHERE id=?";
		Long count = jdbcTemplate.queryForObject(sql, Long.class, id);
		if (count < 2) {
			throw new NullPointerException("库存不足");
		}
		sql = "UPDATE store SET count=count-? WHERE id=?";
		jdbcTemplate.update(sql, 1, id);
	}

	/*
	事务的传播：开启新事务demo：
	@Transactional
	public void doSomething() {
		storeDao.updateStore(1);
		storeDao.updateStore(2);
	}

	@Transactional(propagation= Propagation.REQUIRES_NEW)
	public void updateStore(Integer id) {
		try {
			String sql = "SELECT count FROM store WHERE id=?";
			Long count = jdbcTemplate.queryForObject(sql, Long.class, id);
			if (count < 2) {
				throw new NullPointerException("库存不足");
			}
			sql = "UPDATE store SET count=count-? WHERE id=?";
			jdbcTemplate.update(sql, 1, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	*/

}
