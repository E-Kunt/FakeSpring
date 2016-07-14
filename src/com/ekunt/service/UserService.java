package com.ekunt.service;

import com.ekunt.dao.UserDao;
import com.ekunt.entity.User;

/**
 * UserÒµÎñÂß¼­Àà
 * @author E-Kunt
 *
 */
public class UserService {
	private UserDao dao;
	
	public UserService(UserDao dao) {
		this.dao = dao;
	}

	public UserService() {

	}
	
	public UserDao getDao() {
		return dao;
	}

	public void setDao(UserDao dao) {
		this.dao = dao;
	}
	
	public void add(User user) {
		System.out.println("UserService : ready to add a user!");
		dao.add(user);
	}

}
