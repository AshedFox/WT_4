package bsuir.wt.lab4.service;

import bsuir.wt.lab4.entity.UserDTO;
import bsuir.wt.lab4.entity.User;

public interface UsersService {
	UserDTO authorize(String email, String password) throws ServiceException;
	void register(User user) throws ServiceException;
}
