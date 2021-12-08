package bsuir.wt.lab4.dao.impl;

import bsuir.wt.lab4.dao.DAOException;
import bsuir.wt.lab4.dao.RoomsDAO;
import bsuir.wt.lab4.entity.Room;
import bsuir.wt.lab4.utils.DbHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLRoomsDAO implements RoomsDAO {
    private final static String getRoomsQueryString =
            "SELECT * FROM Rooms;";
    private final static String getRoomQueryString =
            "SELECT * FROM Rooms WHERE id = ?;";
    private final static String insertRoomQueryString =
            "INSERT INTO Rooms (number, description, price) VALUES (?,?,?);";
    private final static String updateRoomQueryString =
            "UPDATE Rooms SET (number, description, price)=(?,?,?) WHERE id = ?;";
    private final static String deleteRoomQueryString =
            "DELETE FROM Rooms WHERE id = ?;";
    private final static String freeRoomQueryString =
            "UPDATE Rooms SET (is_free)=(?) WHERE id = true";

    @Override
    public void freeRoom(int roomId) throws DAOException {
        try(PreparedStatement updateStatement =
                    DbHandler.getInstance().getConnection().prepareStatement(freeRoomQueryString)) {
            updateStatement.setInt(1, roomId);

            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Room> getRooms() throws DAOException {
        try(PreparedStatement statement = DbHandler.getInstance().getConnection().prepareStatement(getRoomsQueryString)) {
            ResultSet resultSet = statement.executeQuery();

            List<Room> rooms = new ArrayList<>();

            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setNumber(resultSet.getString("number"));
                room.setDescription(resultSet.getString("description"));
                room.setPrice(Float.parseFloat(resultSet.getString("price")));
                room.setFree(Integer.parseInt(resultSet.getString("is_free")) > 0);

                rooms.add(room);
            }

            return rooms;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Room getRoom(int id) throws DAOException {
        try(PreparedStatement statement = DbHandler.getInstance().getConnection().prepareStatement(getRoomQueryString)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            Room room;

            if (resultSet.next()) {
                room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setNumber(resultSet.getString("number"));
                room.setDescription(resultSet.getString("description"));
                room.setPrice(Float.parseFloat(resultSet.getString("price")));
                room.setFree(Integer.parseInt(resultSet.getString("is_free")) > 0);

                return room;
            }
            else {
                throw new DAOException("room does not exists");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void create(Room room) throws DAOException {
        try(PreparedStatement statement = DbHandler.getInstance().getConnection().prepareStatement(insertRoomQueryString)) {
            statement.setString(1, room.getNumber());
            statement.setString(2, room.getDescription());
            statement.setFloat(3, room.getPrice());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(int id, Room room) throws DAOException {
        try(PreparedStatement statement = DbHandler.getInstance().getConnection().prepareStatement(updateRoomQueryString)) {
            statement.setString(1, room.getNumber());
            statement.setString(2, room.getDescription());
            statement.setFloat(3, room.getPrice());
            statement.setInt(4, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        try(PreparedStatement statement = DbHandler.getInstance().getConnection().prepareStatement(deleteRoomQueryString)) {
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
