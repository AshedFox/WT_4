package bsuir.wt.lab4.service.impl;

import bsuir.wt.lab4.dao.DAOException;
import bsuir.wt.lab4.dao.DAOProvider;
import bsuir.wt.lab4.dao.RoomsDAO;
import bsuir.wt.lab4.dao.UsersRoomsDAO;
import bsuir.wt.lab4.entity.UserRoom;
import bsuir.wt.lab4.service.BookingService;
import bsuir.wt.lab4.service.ServiceException;

import java.util.List;

public class BookingServiceImpl implements BookingService {
    @Override
    public List<UserRoom> GetUserRooms(int userId) throws ServiceException {
        UsersRoomsDAO usersRoomsDAO = DAOProvider.getInstance().getUsersRoomsDAO();

        try {
            return usersRoomsDAO.getUserRooms(userId);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void BookRoom(int userId, int roomId) throws ServiceException {
        UsersRoomsDAO usersRoomsDAO = DAOProvider.getInstance().getUsersRoomsDAO();

        try {
            usersRoomsDAO.createUserRoom(userId, roomId);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }
}
