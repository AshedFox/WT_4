package bsuir.wt.lab4.dao.impl;

import bsuir.wt.lab4.dao.DAOException;
import bsuir.wt.lab4.dao.UsersRoomsDAO;
import bsuir.wt.lab4.entity.Role;
import bsuir.wt.lab4.entity.Room;
import bsuir.wt.lab4.entity.User;
import bsuir.wt.lab4.entity.UserRoom;
import bsuir.wt.lab4.utils.DbHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLUsersRoomsDAO implements UsersRoomsDAO {
    private final static String createUserRoomQueryString =
            "INSERT INTO UsersRooms (user_id, room_id) VALUES (?, ?);";
    private final static String setRoomReservedQueryString =
            "UPDATE Rooms SET (is_free)=(?) WHERE id = false";
    private final static String getUserRoomsQueryString =
            "SELECT * FROM Users u JOIN UsersRooms ur on u.id = ur.user_id JOIN Rooms r on r.id = ur.room_id WHERE u.id = ?";

    @Override
    public List<UserRoom> getUserRooms(int userId) throws DAOException {
        try(PreparedStatement statement = DbHandler.getInstance().getConnection().prepareStatement(getUserRoomsQueryString)) {
            statement.setInt(1, userId);
            
            ResultSet resultSet = statement.executeQuery();

            List<UserRoom> userRooms = new ArrayList<>();

            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt("r.id"));
                room.setNumber(resultSet.getString("r.number"));
                room.setDescription(resultSet.getString("r.description"));
                room.setPrice(Float.parseFloat(resultSet.getString("r.price")));
                room.setFree(Integer.parseInt(resultSet.getString("r.is_free")) > 0);

                User user = new User();
                user.setId(resultSet.getInt("u.id"));
                user.setEmail(resultSet.getString("u.email"));
                user.setRole(Role.valueOf(resultSet.getString("u.role")));

                userRooms.add(new UserRoom(user, room));
            }

            return userRooms;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void createUserRoom(int userId, int roomId) throws DAOException {
        try(PreparedStatement createStatement =
                    DbHandler.getInstance().getConnection().prepareStatement(createUserRoomQueryString)) {
            createStatement.setInt(1, userId);
            createStatement.setInt(2, roomId);

            createStatement.executeUpdate();

            try(PreparedStatement updateStatement =
                        DbHandler.getInstance().getConnection().prepareStatement(setRoomReservedQueryString)) {
                updateStatement.setInt(1, roomId);

                updateStatement.executeUpdate();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
