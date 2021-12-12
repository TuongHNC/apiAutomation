package database.postgresql;

import com.google.gson.Gson;
import constants.LogConstants;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Repositories {

    private static final Logger LOGGER = LogManager.getLogger(Repositories.class);

    public List<Map<String, Object>> getResult(String query) {
        List<Map<String, Object>> listOfMaps;
        try {
            QueryRunner queryRunner = new QueryRunner();
            LOGGER.info(LogConstants.QUERY_STRING_MESSAGE + query);
            listOfMaps = queryRunner.query(DBConnection.getInstance().get(), query, new MapListHandler());
            LOGGER.info(LogConstants.QUERY_RESULT_MESSAGE + new Gson().toJson(listOfMaps));
        } catch (SQLException e) {
            LOGGER.info(LogConstants.FAILED_QUERY_MESSAGE + e);
            return null;
        }
        return listOfMaps;
    }

    public List<Map<String, Object>> getResultByTable(String tableName, int numberLatestOfRows) {
        String sqlQuery = "Select * from " + tableName + " limit " + numberLatestOfRows;
        return getResult(sqlQuery);
    }

    public int deleteData(String query) {
        int rowsAffected = 0;
        try {
            PreparedStatement prepareStatement = DBConnection.getInstance().get().prepareStatement(query);
            rowsAffected = prepareStatement.executeUpdate();
            LOGGER.info(String.format(LogConstants.DELETE_RESULT_MESSAGE, rowsAffected));
        } catch (SQLException e) {
            LOGGER.info(LogConstants.FAILED_QUERY_MESSAGE + e);
        }
        return rowsAffected;
    }

}


