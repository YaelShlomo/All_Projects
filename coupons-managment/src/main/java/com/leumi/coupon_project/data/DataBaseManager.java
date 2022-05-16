package com.leumi.coupon_project.data;

import com.leumi.coupon_project.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A singleton class that manages the connection to the database.
 *
 * @author Rami
 *
 */
public class DataBaseManager {
    private static DataBaseManager instance = null;

    private DataBaseManager() throws ClassNotFoundException, SQLException {
        super();
    }

    public static DataBaseManager getInstance(){
        if (instance == null) {
            try {
                instance = new DataBaseManager();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public ResultSet runGetQuery(String sql) throws SQLException {
        System.out.println("runGetQuarry()= " + sql);
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultset = preparedStatement.executeQuery();
        System.out.println("runGetQuarry() done!");
        return resultset;
    }
    public int runUpdateQuery(String sql) throws SQLException {
        System.out.println("runUpdateQuery()= " + sql);
        PreparedStatement preparedStatement = ConnectionPool.getConnection().prepareStatement(sql);
        int resultset = preparedStatement.executeUpdate(sql);
        System.out.println("runUpdateQuery() done!");
        return resultset;
    }
}


















//package coupon_project.data;
//
//import java.sql.*;
//
///**
// * A singleton class that manages the connection to the database.
// *
// * @author Rami
// *
// */
//public class DataBaseManager {
//	private static DataBaseManager instance = null;
//
//	private static final String DOMAIN_STRING = "localhost";// 127.0.0.1
//	private static final String DB_NAME = "coupon_management";
//	private static final String USER_NAME = "root";
//	private static final String PASSWORD = "1234";
//	private static final String CONNECTION_STRING = "jdbc:mysql://" + DOMAIN_STRING + "/" + DB_NAME + "?user="
//			+ USER_NAME + "&password=" + PASSWORD;
//	private Connection connection;
//
//	private DataBaseManager() throws ClassNotFoundException, SQLException {
//		super();
//		initDatabase();
//	}
//
//	public static DataBaseManager getInstance(){
//		if (instance == null) {
//			try {
//				instance = new DataBaseManager();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return instance;
//	}
//
//	private void initDatabase() throws ClassNotFoundException, SQLException {
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		System.out.println("JDBC driver loaded successfully!");
//		System.out.println("Connectiong to database: " + CONNECTION_STRING);
//		connection = DriverManager.getConnection(CONNECTION_STRING);
//		System.out.println("Connected to database " + DB_NAME);
//	}
//
//
//	public ResultSet runGetQuarry(String sql) throws SQLException {
//		System.out.println("runGetQuarry()= " + sql);
//		PreparedStatement preparedStatement = connection.prepareStatement(sql);
//		ResultSet resultset = preparedStatement.executeQuery();
//		//From outside
////		while (resultset.next()) {
////			EmploeeEntity emp = new EmploeeEntity();
////			int id = resultset.getInt("id");
////			emp.setId(id);
////			String name = resultset.getString("name");
////			emp.setName(name);
////			int salary = resultset.getInt("salary");
////			emp.setSalary(salary);
////			resList.add(emp);
////		}
//		System.out.println("runGetQuarry() done!");
//		return resultset;
//	}
//}
