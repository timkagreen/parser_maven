package parser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ToIngredDB {
    private static final String URL = "jdbc:mysql://localhost:3306/recipesdb?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" + "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1002199Timka";

    private static final String INSERT_NEW = "INSERT INTO allingredints VALUES(?)";

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public ToIngredDB(){
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (connection.isClosed()) {
                System.out.println("Соединение с БД не установлено");
            } else
                System.out.println("Соединение с БД установлено!!!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }



    public void PutToDB(String ingredients){
        try {
            preparedStatement = connection.prepareStatement(INSERT_NEW);

            preparedStatement.setString(1, ingredients);

            preparedStatement.execute();
            preparedStatement.clearParameters();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public void CloseConnection(){
        try {
            connection.close();
            if (connection.isClosed()) {
                System.out.println("Соединение с БД закрыто");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

