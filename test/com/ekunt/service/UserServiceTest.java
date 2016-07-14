package com.ekunt.service;

import org.junit.Test;
import com.ekunt.dao.impl.UserDaoImpl;
import com.ekunt.entity.User;
import com.ekunt.fakespring.ClassPathXmlApplicationContext;

/**
 * ≤‚ ‘¿‡
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
	
}
