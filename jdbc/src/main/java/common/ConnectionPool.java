package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;



public class ConnectionPool implements AutoCloseable {
    private BlockingQueue<PooledConnection> connectionQueue;
    private static ConnectionPool instance;
    private static final Logger log = LogManager.getLogger(ConnectionPool.class);

    public static ConnectionPool getInstance(String pathConfig) {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    log.debug("instance of connection pool inited");
                    instance = new ConnectionPool(pathConfig);
                }
            }
        }
        return instance;
    }

    private ConnectionPool(String pathToConfig) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(pathToConfig));
            Class.forName(properties.getProperty("driver"));
        } catch (ClassNotFoundException | IOException e) {
            log.error("Cannon init constructor for connection pool", e);
        }

        String url = properties.getProperty("url");
        int poolSize = Integer.parseInt(properties.getProperty("poolSize"));

        connectionQueue = new ArrayBlockingQueue<>(poolSize);

        for (int i = 0; i < poolSize; i++) {
            try {
                connectionQueue.add(new PooledConnection(DriverManager.getConnection(url, properties), this));
            } catch (SQLException e) {
               log.error("cannot add connection to connection pool", e);
            }
        }
    }

   public Connection getConnection() throws InterruptedException {
        return connectionQueue.take();
    }

    void acceptConnection(PooledConnection connection) {
        connectionQueue.offer(connection);
    }

    @Override
    public void close() throws Exception {
        for (PooledConnection connection : connectionQueue) {
            connection.reallyClose();
        }
    }
}

