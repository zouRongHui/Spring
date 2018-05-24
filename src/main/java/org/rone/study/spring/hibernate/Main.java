package org.rone.study.spring.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new  ClassPathXmlApplicationContext("/applicationContext.xml");
		
		StoreService storeService = context.getBean(StoreService.class);
		storeService.doSomething();
		
		((AbstractApplicationContext)context).close();
	}

}

@Service
class StoreService {
	
	@Autowired
	private StoreDao storeDao;

	public void doSomething() {
		storeDao.updateStore(1);
		storeDao.updateStore(2);
	}
	
}

@Repository
class StoreDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void updateStore(Integer id) {
		String hql = "SELECT s.count FROM Store s WHERE s.id=:id";
		Integer count = (Integer) getSession().createQuery(hql).setParameter("id", id).uniqueResult();
		if (count < 2) {
			throw new NullPointerException("库存不足");
		}
		hql = "UPDATE Store s SET s.count=s.count-1 WHERE s.id=:id";
		getSession().createQuery(hql).setParameter("id", id).executeUpdate();
	}

}

@Entity
@Table(name="store")
class Store {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="name")
	private String name;
	@Column(name="count")
	private Integer count;
	
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "Store [id=" + id + ", name=" + name + ", count=" + count + "]";
	}
}
