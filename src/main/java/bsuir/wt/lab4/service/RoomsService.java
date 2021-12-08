package bsuir.wt.lab4.service;

import bsuir.wt.lab4.entity.Room;

import java.util.List;

public interface RoomsService {

    List<Room> getRooms() throws ServiceException;

    Room getRoom(int id) throws ServiceException;

    void createRoom(Room room) throws ServiceException;

    void editRoom(int id, Room room) throws ServiceException;

    void deleteRoom(int id) throws ServiceException;
}
