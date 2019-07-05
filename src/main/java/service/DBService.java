package service;

//import java.sql.Driver;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DBService {
//    private final Connection connection;
//
//    public DBService() {
//        this.connection = getMysqlConnection();
//    }
//
//    public Product getProduct(String name) throws DBException {
//        try {
//            return (new ProductDaoImpl(connection).getProduct(name));
//        } catch (SQLException e) {
//            throw new DBException(e);
//        }
//    }
//
//    public Boolean addProduct(Product name) throws DBException {
//        try {
//            connection.setAutoCommit(false);
//            ProductDaoImpl dao = new ProductDaoImpl(connection);
//            dao.createTable();
//            dao.addProduct(name);
//            connection.commit();
//            return true;
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException ignore) {
//            }
//            throw new DBException(e);
//        } finally {
//            try {
//                connection.setAutoCommit(true);
//            } catch (SQLException ignore) {
//            }
//        }
//    }


//}
