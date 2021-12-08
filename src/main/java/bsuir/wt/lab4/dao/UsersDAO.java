package bsuir.wt.lab4.dao;

import bsuir.wt.lab4.entity.User;

public interface UsersDAO {
	User authorize(String email, String password) throws DAOException;
	void register(User userData) throws DAOException;
}
