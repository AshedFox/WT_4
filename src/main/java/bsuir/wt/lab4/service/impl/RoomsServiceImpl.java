package bsuir.wt.lab4.service.impl;

import bsuir.wt.lab4.dao.DAOException;
import bsuir.wt.lab4.dao.DAOProvider;
import bsuir.wt.lab4.dao.RoomsDAO;
import bsuir.wt.lab4.entity.Room;
import bsuir.wt.lab4.entity.UserDTO;
import bsuir.wt.lab4.service.RoomsService;
import bsuir.wt.lab4.service.ServiceException;

import java.util.List;

public class RoomsServiceImpl implements RoomsService {
    @Override
    public List<Room> getRooms() throws ServiceException {
        RoomsDAO roomsDAO = DAOProvider.getInstance().getRoomsDAO();

        try {
            return roomsDAO.getRooms();
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public Room getRoom(int id) throws ServiceException {
        RoomsDAO roomsDAO = DAOProvider.getInstance().getRoomsDAO();

        try {
            return roomsDAO.getRoom(id);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void createRoom(Room room) throws ServiceException {
        RoomsDAO roomsDAO = DAOProvider.getInstance().getRoomsDAO();

        try {
            roomsDAO.create(room);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void editRoom(int id, Room room) throws ServiceException {
        if (id != room.getId()) {
            throw new ServiceException();
        }

        RoomsDAO roomsDAO = DAOProvider.getInstance().getRoomsDAO();

        try {
            roomsDAO.update(id, room);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteRoom(int id) throws ServiceException {
        RoomsDAO roomsDAO = DAOProvider.getInstance().getRoomsDAO();

        try {
            roomsDAO.delete(id);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }
}
