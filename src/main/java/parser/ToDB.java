package parser;

import java.sql.*;

public class ToDB {
    private static final String URL = "jdbc:mysql://localhost:3306/recipesdb?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" + "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1002199Timka";

    private static final String INSERT_NEW = "INSERT INTO recipelist VALUES(?,?,?,?)";
    private static final String INSERT_NEW_INGREDIENT = "INSERT INTO allingredints VALUES(?)";
    private static final String INSERT_NEW_INGREDS = "INSERT INTO ingred VALUES(?,?,?)";



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



    public void PutToDB(int recipeID,String title,String instruction,String urlJpg){
        try {
            preparedStatement = connection.prepareStatement(INSERT_NEW);
            preparedStatement.setString(1, Integer.toString(recipeID));
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, instruction);
            preparedStatement.setString(4, urlJpg);

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

    public void PutToDB(int recipeID,String ingredient, String quantity){
        try {
            preparedStatement = connection.prepareStatement(INSERT_NEW_INGREDS);

            preparedStatement.setString(1, Integer.toString(recipeID));
            preparedStatement.setString(2, ingredient);
            preparedStatement.setString(3, quantity);

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
