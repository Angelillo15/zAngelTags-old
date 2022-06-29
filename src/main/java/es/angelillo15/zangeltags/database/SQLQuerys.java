package es.angelillo15.zangeltags.database;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.sql.*;
import java.util.UUID;

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
    public static void insertData(Connection connection, UUID uuid, String tag){
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `user_tags` (`ID`, `UUID`, `Tag`) VALUES (null, ?, ?);");
            statement.setString(1, uuid.toString());
            statement.setString(2, tag);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean playerInDB(Connection connection, UUID uuid){
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user_tags WHERE UUID=?");
            statement.setString(1, uuid.toString());
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
    public static void updateData(Connection connection, UUID uuid, String tag){
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE `user_tags` SET `Tag` = ? WHERE UUID=?");
            statement.setString(1, tag);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public static String getTag(Connection connection, UUID uuid){
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT Tag From `user_tags` WHERE UUID=?");

            statement.setString(1, uuid.toString());
            ResultSet result = statement.executeQuery();
            if(result.next()){
                String tag = result.getString("Tag");

                return tag;
            }

        }catch (SQLException e){
            Bukkit.getConsoleSender().sendMessage(String.valueOf(e));
            throw new RuntimeException(e);

        }
        return "null";

    }
    public static void CloseConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(e));
        }
    }

}
