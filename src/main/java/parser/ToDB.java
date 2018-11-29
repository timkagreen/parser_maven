package parser;

import java.sql.*;

public class ToDB {
    private static final String URL = "jdbc:mysql://localhost:3306/recipesdb?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" + "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1002199Timka";

    private static final String INSERT_NEW = "INSERT INTO recipelist(recipeName,recipeInstruction,recipeImage) VALUES(?,?,?)";
    private static final String INSERT_NEW_INGREDIENT = "INSERT INTO allingredints VALUES(?)";


    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public ToDB(){
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



    public void PutToDB(String title,String instruction,String urlJpg){
        try {
            preparedStatement = connection.prepareStatement(INSERT_NEW);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, instruction);
            preparedStatement.setString(3, urlJpg);

            preparedStatement.execute();
            preparedStatement.clearParameters();
        } catch (
                SQLException e) {
            //e.printStackTrace();
        }
    }


    public void PutToDB(String ingredients){
        try {
            preparedStatement = connection.prepareStatement(INSERT_NEW_INGREDIENT);

            preparedStatement.setString(1, ingredients);

            preparedStatement.execute();
            preparedStatement.clearParameters();
        } catch (
                SQLException e) {
            //System.out.println("*");
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
