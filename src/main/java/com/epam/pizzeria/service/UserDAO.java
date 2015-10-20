package com.epam.pizzeria.service;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.pizzeria.model.User;

@Repository(value = "userDAO")
@Transactional
// @Configuration
public class UserDAO implements UserService, UserDetailsService {
	@Autowired
	@Qualifier(value = "sessionFactory")
	SessionFactory sf;

	public List<User> findAll() throws SQLException {
		return getSession().createQuery("from users").list();
	}

	public User findUser(User user) throws CustomerNotFoundException, SQLException {
		String query = String.join("", "select from users where Email=", user.getEmail(), " and password=",
				user.getPassword());
		List<User> users = getSession().createQuery(query).list();

		return users.get(0);
	}

	public User attemptAddUser(User user) throws UserAlreadyExistException, SQLException {
		checkIfUserExists(user);
		// TODO: add user
		return user;
	}

	public int save(User user) {
		return (Integer) getSession().save(user);
	}

	public User findUserById(Integer id) {
		return (User) getSession().get(User.class, id);
	}

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Query q = getSession().createQuery("from User u where u.name = :name");
		q.setString("name", userName);
		
		List<User> users = q.list();
		checkIfUserListIsEmpty(users, userName);
		return users.get(0);
	}

	private void checkIfUserExists(User user) {
		String query = String.join("", "select from users where Email=", user.getEmail(), " and password=",
				user.getPassword());
		List<User> users = getSession().createQuery(query).list();
		if (!users.isEmpty()) {
			throw new UserAlreadyExistException();
		}
	}
	
	private void checkIfUserListIsEmpty(List<User> users, String userName) {
		if (users.isEmpty()) {
			throw new UsernameNotFoundException(String.join("", "User ", userName, " not found"));
		}
	}
	
	private Session getSession() {
		return sf.getCurrentSession();
	}
}
