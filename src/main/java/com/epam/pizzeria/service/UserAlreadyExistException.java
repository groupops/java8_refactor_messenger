package com.epam.pizzeria.service;

import javax.persistence.EntityExistsException;

public class UserAlreadyExistException extends EntityExistsException {
}
