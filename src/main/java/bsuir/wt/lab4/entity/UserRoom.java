package bsuir.wt.lab4.entity;

import java.io.Serializable;
import java.util.StringJoiner;

public class UserRoom implements Serializable {
    private User user;
    private Room room;

    public UserRoom(User user, Room room) {
        this.user = user;
        this.room = room;
    }

    public UserRoom() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRoom)) return false;

        UserRoom userRoom = (UserRoom) o;

        if (!user.equals(userRoom.user)) return false;
        return room.equals(userRoom.room);
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + room.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserRoom.class.getSimpleName() + "[", "]")
                .add("user=" + user)
                .add("room=" + room)
                .toString();
    }
}
