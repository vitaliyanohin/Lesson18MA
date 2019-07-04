package service;

import dao.impl.ProductDaoImpl;
import dao.impl.UserDaoImpl;
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
            return (new ProductDaoImpl(connection).getProduct(name));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public Boolean addProduct(Product name) throws DBException {
        try {
            connection.setAutoCommit(false);
            ProductDaoImpl dao = new ProductDaoImpl(connection);
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
            return (new UserDaoImpl(connection).getUser(name));
        } catch (SQLException e) {
            throw new DBException(e);
        }

    }
    public User getUser(Long id) throws DBException {
        try {
            return (new UserDaoImpl(connection).getUser(id));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
    public int count() {
        try {
            return new UserDaoImpl(connection).count();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Boolean addUser(User name) throws DBException {
        try {
            connection.setAutoCommit(false);
            UserDaoImpl dao = new UserDaoImpl(connection);
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
        UserDaoImpl dao = new UserDaoImpl(connection);
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
