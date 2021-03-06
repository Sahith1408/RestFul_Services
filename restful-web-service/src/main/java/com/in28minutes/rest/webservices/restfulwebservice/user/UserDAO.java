package com.in28minutes.rest.webservices.restfulwebservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDAO {
	
	private static long usersCount = 2;

	private static List<User> users = new ArrayList<User>();
	
	static {
		users.add(new User(001L, "sahith", new Date()));
		users.add(new User(002L, "manchi", new Date()));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public static User save(User user) {
		if (user.getId() == null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	} 
	
	public User findOne(long id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteById(long id) {
		
			Iterator<User> iterator = users.iterator();
			while (iterator.hasNext()) {
				User user = iterator.next();
				if (user.getId() == id) {
					iterator.remove();
					return user;
				}
		}
		return null;
	}
}
