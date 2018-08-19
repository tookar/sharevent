package de.tolina.sharevent.api.user;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock of the user management.
 */
@SuppressWarnings("javadoc")
public class UserFinder {

	private List<User> users = new ArrayList<>();

	public UserFinder() {
		User user = new User();
		user.setName("Hans Sarpei");
		user.setPhone("+49 158 795 253 446");
		users.add(user);

		user = new User();
		user.setName("Fred Feuerstein");
		user.setPhone("+49 163 992 002 112");
		users.add(user);

		user = new User();
		user.setName("Chuck Norris");
		user.setPhone("+49 178 456 332 189");
		users.add(user);

		user = new User();
		user.setName("Peter Pan");
		user.setPhone("+49 177 547 115 246");
		users.add(user);
	}

	public List<User> findUser() {
		return users;
	}
}
