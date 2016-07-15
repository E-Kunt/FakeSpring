package com.ekunt.service;

import java.lang.reflect.Proxy;

import org.junit.Test;

import com.ekunt.dao.UserDao;
import com.ekunt.dao.impl.UserDaoImpl;
import com.ekunt.entity.User;
import com.ekunt.fakespring.ClassPathXmlApplicationContext;
import com.ekunt.interceptor.LogInterceptor;

/**
 * 测试类
 * @author E-Kunt
 *
 */
public class UserServiceTest {

	@Test
	public void testWithoutFakeSpring() {
		UserService service = new UserService(new UserDaoImpl());
		service.add(new User());
	}

	@Test
	public void testWithFakeSpring() throws Exception{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
		UserService service = (UserService) context.getBean("userService");
		service.add(new User());
	}
	
	/**
	 * 模拟Spring的AOP
	 * @throws Exception
	 */
	@Test
	public void testWithAOPInterceptor() throws Exception{
		//先获得被代理对象
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
		UserDao userDao = (UserDao) context.getBean("userDao");
		//创建处理对象，把被代理对象设进去
		LogInterceptor log = new LogInterceptor();
		log.setTarget(userDao);
		//通过Proxy，生成动态代理对象。新的对象，方法中包含新逻辑。
		UserDao userDaoProxy = (UserDao)Proxy.newProxyInstance(userDao.getClass().getClassLoader(), userDao.getClass().getInterfaces(), log);
		//调用代理对象的方法。
		userDaoProxy.add(new User());
		
		//验证代理对象类型。
		System.out.println(userDaoProxy.getClass());
	}
	
}
