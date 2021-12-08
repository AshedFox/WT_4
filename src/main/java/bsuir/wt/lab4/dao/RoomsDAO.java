package bsuir.wt.lab4.dao;

import bsuir.wt.lab4.entity.Room;

import java.util.List;

public interface RoomsDAO {
    void freeRoom(int roomId) throws DAOException;
    List<Room> getRooms() throws DAOException;
    Room getRoom(int id) throws DAOException;
    void create(Room room) throws DAOException;
    void update(int id, Room room) throws DAOException;
    void delete(int id) throws DAOException;
}
