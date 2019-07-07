package factory;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetSQLConnectionFactory {

  private static final Logger LOGGER =  Logger.getLogger(GetSQLConnectionFactory.class);

  public static Connection getMysqlConnection() {
    try {
      DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

      StringBuilder url = new StringBuilder();

      url.
              append("jdbc:mysql://").        //db type
              append("localhost:").           //host name
              append("3306/").                //port
              append("mysql?").          //db name
              append("user=user&").          //login
              append("password=user");       //password
      System.out.println("URL: " + url + "\n");
      Connection connection = DriverManager.getConnection(url.toString());
      try {
        System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
        System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
        System.out.println("Driver: " + connection.getMetaData().getDriverName());
        System.out.println("Autocommit: " + connection.getAutoCommit());
      } catch (SQLException e) {
         LOGGER.log(Level.ALL, "Error: ", e);
      }
      return connection;
    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
       LOGGER.log(Level.ALL, "Error: ", e);
    }
    return null;
  }
}
