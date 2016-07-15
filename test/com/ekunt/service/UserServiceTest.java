package com.ekunt.service;

import java.lang.reflect.Proxy;

import org.junit.Test;

import com.ekunt.dao.UserDao;
import com.ekunt.dao.impl.UserDaoImpl;
import com.ekunt.entity.User;
import com.ekunt.fakespring.ClassPathXmlApplicationContext;
import com.ekunt.interceptor.LogInterceptor;

/**
 * ������
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
	 * ģ��Spring��AOP
	 * @throws Exception
	 */
	@Test
	public void testWithAOPInterceptor() throws Exception{
		//�Ȼ�ñ��������
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
		UserDao userDao = (UserDao) context.getBean("userDao");
		//����������󣬰ѱ�����������ȥ
		LogInterceptor log = new LogInterceptor();
		log.setTarget(userDao);
		//ͨ��Proxy�����ɶ�̬��������µĶ��󣬷����а������߼���
		UserDao userDaoProxy = (UserDao)Proxy.newProxyInstance(userDao.getClass().getClassLoader(), userDao.getClass().getInterfaces(), log);
		//���ô������ķ�����
		userDaoProxy.add(new User());
		
		//��֤����������͡�
		System.out.println(userDaoProxy.getClass());
	}
	
}
