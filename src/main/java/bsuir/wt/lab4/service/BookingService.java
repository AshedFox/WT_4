package bsuir.wt.lab4.service;

import bsuir.wt.lab4.entity.UserRoom;

import java.util.List;

public interface BookingService {
    List<UserRoom> GetUserRooms(int userId) throws ServiceException;
    void BookRoom(int userId, int roomId) throws ServiceException;
}
