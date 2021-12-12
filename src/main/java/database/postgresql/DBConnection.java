package database.postgresql;

import apis.AbstractBaseRequest;
import constants.LogConstants;
import database.IDatabase;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;


public class DBConnection extends AbstractBaseRequest implements IDatabase<Connection> {

    private static final Logger LOGGER = LogManager.getLogger(DBConnection.class);

    private static DBConnection dbConnection;
    private static Connection connection;

    private DBConnection() {
    }

    public static DBConnection getInstance() {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public Connection get() {
        try {
            if (connection == null || connection.isClosed()) {
                connect();
            }
        } catch (SQLException e) {
            LOGGER.error(LogConstants.FAILED_CONNECTED_DB_MESSAGE + e);
        }
        return connection;
    }

    public Connection connect() {
        try {
            LOGGER.info(LogConstants.CONNECT_TO_DB_MESSAGE);
            connection = DriverManager.getConnection(environmentHandler.getEnvironmentData("dbUrl"),
                    environmentHandler.getEnvironmentData("dbUserName"),
                    environmentHandler.getEnvironmentData("dbPassword"));
            LOGGER.info(LogConstants.SUCCESS_CONNECT_TO_POSGRE_SQL_DB_MESSAGE);
            return connection;
        } catch (SQLException e) {
            LOGGER.error(LogConstants.FAILED_CONNECTED_DB_MESSAGE + e);
        }
        return null;
    }

    public Connection reconnect(int tryInSeconds) {
        for (int i = 0; i < tryInSeconds; i++) {
            connect();
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
        }
        return get();
    }

    public void close() {
        try {
            connection.close();
            LOGGER.info(LogConstants.CLOSED_CONNECTED_DATABASE_MESSAGE);
        } catch (SQLException e) {
            LOGGER.info(LogConstants.FAILED_CLOSED_CONNECTED_DATABASE_MESSAGE + e);
        }
    }
}


