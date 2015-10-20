package com.epam.pizzeria.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import com.epam.pizzeria.model.User;

@Transactional
public interface UserService {

    User find(Integer id);
    List<User> findAll() throws SQLException;
    User findUser(User us) throws SQLException, CustomerNotFoundException;
    User attemptAddUser(User us) throws SQLException, UserAlreadyExistException;
    int save(User u);

    default User findFlowUser() throws SQLException, CustomerNotIdentityException {
    	User activeUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	return activeUser;
    };
}
