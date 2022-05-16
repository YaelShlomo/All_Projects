package com.leumi.coupon_project.DAO;

import com.leumi.coupon_project.data.Coupon;
import com.leumi.coupon_project.data.Customer;
import com.leumi.coupon_project.helpers.Credentials;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO {
    int add(Customer customer) throws SQLException;
    void remove(int id) throws SQLException;
    Customer getOneCustomer(int id);
    ArrayList<Coupon> getCouponsOfCustomer(int customerId) throws SQLException, ClassNotFoundException;
    ArrayList<Coupon> getCouponsByCategory(int customerId, int categoryId) throws SQLException, ClassNotFoundException;
    ArrayList<Coupon> getCouponsByMaxPrice(int customerId, double maxPrice) throws SQLException, ClassNotFoundException;
    void update(Customer customer) throws SQLException;
    void updateCouponOfCustomers(int customerId, int couponId) throws SQLException;
    ArrayList<Customer> getAllCustomers();
    int isCustomerExists(Credentials credentials);
    void deleteCouponPurchaseHistory(int customerID) throws SQLException;
}














//package coupon_project.DAO;
//
//import com.leumi.coupon_project.data.Customer;
//import coupon_project.data.Customer;
//
//import java.sql.SQLException;
//
//
//public interface CustomerDAO {
//    //CRUD functions
//
//    int add(Customer entity) throws SQLException;
//    void remove(int id) throws SQLException;
//    Customer get(int id);
//    void update(Customer entity) throws SQLException;
//
//}
