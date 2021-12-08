package bsuir.wt.lab4.dao.impl;

import java.sql.*;

import bsuir.wt.lab4.dao.DAOException;
import bsuir.wt.lab4.dao.UsersDAO;
import bsuir.wt.lab4.entity.Role;
import bsuir.wt.lab4.entity.User;
import bsuir.wt.lab4.utils.DbHandler;

public class SQLUsersDAO implements UsersDAO {

	private static final String authorizeQueryString =
			"SELECT * FROM Users WHERE email = ? AND password = ?;";
	private static final String registerQueryString =
			"INSERT INTO Users (email, password) VALUES (?, ?);";


	@Override
	public User authorize(String email, String password)  throws DAOException {
		try(PreparedStatement statement = DbHandler.getInstance().getConnection().prepareStatement(authorizeQueryString)) {
			statement.setString(1, email);
			statement.setString(2, password);

			ResultSet resultSet = statement.executeQuery();

			User user;

			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setRole(Role.valueOf(resultSet.getString("role")));

				return user;
			}
			else {
				throw new DAOException("user doesn't exists");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void register(User userData) throws DAOException {
		try(PreparedStatement statement = DbHandler.getInstance().getConnection().prepareStatement(registerQueryString)) {
			statement.setString(1, userData.getEmail());
			statement.setString(2, userData.getPassword());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
}

