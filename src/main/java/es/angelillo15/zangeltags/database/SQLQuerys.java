package es.angelillo15.zangeltags.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLQuerys {
    public static boolean tablesCreated(Connection connection){
        try {
            PreparedStatement statement = connection.prepareStatement("SHOW TABLES LIKE 'user_tags';");
            ResultSet result = statement.executeQuery();
            if(result.next()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void createTables(Connection connection){
        try {
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE `user_tags` (`ID` INT(11) NOT NULL AUTO_INCREMENT,`UUID` VARCHAR(50) NULL DEFAULT '' COLLATE 'utf8mb4_general_ci',`Tag` VARCHAR(100) NULL DEFAULT '' COLLATE 'utf8mb4_general_ci',PRIMARY KEY (`ID`) USING BTREE)");
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
