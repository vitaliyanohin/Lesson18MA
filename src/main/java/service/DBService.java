package service;

import dao.ProductDao;
import dao.UserDao;
import model.Product;
import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {
    private final Connection connection;

    public DBService() {
        this.connection = getMysqlConnection();
    }

    public Product getProduct(String name) throws DBException {
        try {
            return (new ProductDao(connection).getProduct(name));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public Boolean addProduct(Product name) throws DBException {
        try {
            connection.setAutoCommit(false);
            ProductDao dao = new ProductDao(connection);
            dao.createTable();
            dao.insertUser(name);
            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }
    public User getUser(String name) throws DBException {
        try {
            return (new UserDao(connection).getUser(name));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public Boolean addUser(User name) throws DBException {
        try {
            connection.setAutoCommit(false);
            UserDao dao = new UserDao(connection);
            dao.createTable();
            dao.insertUser(name);
            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public void cleanUp() throws DBException {
        UserDao dao = new UserDao(connection);
        try {
            dao.dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void printConnectInfo() {
        try {
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("UnusedDeclaration")
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
            return connection;
        } catch ( InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
