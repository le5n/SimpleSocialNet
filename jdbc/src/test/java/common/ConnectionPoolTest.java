package common;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class ConnectionPoolTest {
    static private ConnectionPool connectionPool;

    // TODO: 25.10.2016 rewrite with user data
    @BeforeClass
    public static void init() {
        String pathToConfig = "D:\\Программы\\SimpleSocialNet\\jdbc\\src\\test\\resources\\db.properties";
        connectionPool = ConnectionPool.getInstance(pathToConfig);
    }

    @Test
    public void connectionTest() throws Exception {
        Collection<Book> books;

        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM library.`anti-utopias`;")) {
            books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(
                        new Book(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("author")
                        ));
            }
        }
        Assert.assertTrue(books.contains(new Book(1, "1984", "George Orwell")));
    }
}