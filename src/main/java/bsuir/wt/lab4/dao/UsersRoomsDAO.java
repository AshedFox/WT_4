package bsuir.wt.lab4.dao;

import bsuir.wt.lab4.entity.UserRoom;

import java.util.List;

public interface UsersRoomsDAO {
    List<UserRoom> getUserRooms(int userId) throws DAOException;
    void createUserRoom(int userId, int roomId) throws DAOException;
}
