package bsuir.wt.lab4.dao;

import bsuir.wt.lab4.dao.impl.SQLRoomsDAO;
import bsuir.wt.lab4.dao.impl.SQLUsersDAO;
import bsuir.wt.lab4.dao.impl.SQLUsersRoomsDAO;

public class DAOProvider {
	private static final DAOProvider instance = new DAOProvider();
	
	private final UsersDAO usersDAO = new SQLUsersDAO();
	private final RoomsDAO roomsDAO = new SQLRoomsDAO();
	private final UsersRoomsDAO usersRoomsDAO = new SQLUsersRoomsDAO();

	private DAOProvider () {}
	
	public UsersDAO getUsersDAO() {
		return usersDAO;
	}
	
	public static DAOProvider getInstance() {
		return instance;
	}

	public RoomsDAO getRoomsDAO() {
		return roomsDAO;
	}

	public UsersRoomsDAO getUsersRoomsDAO() {
		return usersRoomsDAO;
	}
}
