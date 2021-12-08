package bsuir.wt.lab4.entity;

import java.io.Serializable;
import java.util.StringJoiner;

public class Room implements Serializable {
    private int id;
    private String number;
    private String description;
    private float price;
    private boolean isFree;

    public Room(int id, String number, String description, float price, boolean isFree) {
        this.id = id;
        this.number = number;
        this.description = description;
        this.price = price;
        this.isFree = isFree;
    }

    public Room() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;

        Room room = (Room) o;

        if (id != room.id) return false;
        if (Float.compare(room.price, price) != 0) return false;
        if (isFree != room.isFree) return false;
        if (!number.equals(room.number)) return false;
        return description.equals(room.description);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + number.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (isFree ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Room.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("number='" + number + "'")
                .add("description='" + description + "'")
                .add("price=" + price)
                .add("isFree=" + isFree)
                .toString();
    }
}
