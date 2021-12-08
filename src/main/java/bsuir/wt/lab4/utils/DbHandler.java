package bsuir.wt.lab4.utils;

import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbHandler {
    private static final String connectionString = "jdbc:sqlite:identifier.sqlite";

    private static final String createQueryString =
            "create table if not exists Rooms\n" +
            "(" +
            "    id          integer" +
            "        primary key autoincrement," +
            "    number      text not null," +
            "    description text    default '' not null," +
            "    price       real not null," +
            "    is_free     boolean default true not null" +
            ");" +
            "" +
            "create table if not exists  Users" +
            "(" +
            "    id       integer" +
            "        primary key autoincrement," +
            "    email    text not null," +
            "    password text not null," +
            "    role     text default 'USER' not null" +
            ");" +
            "" +
            "create unique index if not exists Users_email_uindex" +
            "    on Users (email);" +
            "" +
            "create table if not exists  UsersRooms" +
            "(" +
            "    user_id integer not null" +
            "        references Users," +
            "    room_id integer not null" +
            "        references Rooms" +
            ");";

    private static DbHandler instance;
    private final Connection connection;

    public static synchronized DbHandler getInstance() throws SQLException {
        if (instance == null) {
            instance = new DbHandler();
        }
        return instance;
    }

    private DbHandler() throws SQLException {
        DriverManager.registerDriver(new JDBC());
        connection = DriverManager.getConnection(connectionString);

        try (PreparedStatement statement = connection.prepareStatement(createQueryString)){
            statement.executeUpdate();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
