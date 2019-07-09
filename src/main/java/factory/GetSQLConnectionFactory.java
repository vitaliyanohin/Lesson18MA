package factory;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetSQLConnectionFactory {

  private static final Logger LOGGER =  Logger.getLogger(GetSQLConnectionFactory.class);
  public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

  public static Connection getMysqlConnection() {
    try {
      DriverManager.registerDriver((Driver) Class.forName(JDBC_DRIVER).newInstance());

      StringBuilder url = new StringBuilder();

      url.
              append("jdbc:mysql://").        //db type
              append("localhost:").           //host name
              append("3306/").                //port
              append("mysql?").          //db name
              append("user=user&").          //login
              append("password=user");       //password
      LOGGER.info("URL: " + url + "\n");
      Connection connection = DriverManager.getConnection(url.toString());
      try {
        LOGGER.info("DB name: " + connection.getMetaData().getDatabaseProductName());
        LOGGER.info("DB version: " + connection.getMetaData().getDatabaseProductVersion());
        LOGGER.info("Driver: " + connection.getMetaData().getDriverName());
        LOGGER.info("Autocommit: " + connection.getAutoCommit());
      } catch (SQLException e) {
         LOGGER.log(Level.ERROR, "Failed to get table status: ", e);
      }
      return connection;
    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
       LOGGER.log(Level.ERROR, "Failed to get connection: ", e);
    }
    return null;
  }
}
