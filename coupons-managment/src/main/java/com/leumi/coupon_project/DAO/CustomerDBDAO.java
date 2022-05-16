package com.leumi.coupon_project.DAO;

import com.leumi.coupon_project.data.Coupon;
import com.leumi.coupon_project.data.Customer;
import com.leumi.coupon_project.data.DataBaseManager;
import com.leumi.coupon_project.helpers.Credentials;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDBDAO implements CustomerDAO {

    private static CustomerDBDAO instance;

    private CustomerDBDAO() {
        super();
    }

    private String sql;

    DataBaseManager dbm = DataBaseManager.getInstance();


    public static CustomerDBDAO getInstance() {
        if (instance == null) {
            instance = new CustomerDBDAO();
        }
        return instance;
    }

    //aux func:
    public boolean idExists(int id) {
        return this.getOneCustomer(id) != null;
    }

    public void remove(int id) throws SQLException {
        sql = "DELETE FROM customers WHERE id=" + id;
        int result = dbm.runUpdateQuery(sql);
        System.out.println("ID: " + id + " removed");
    }

    public ArrayList<Coupon> getCouponsOfCustomer(int customerId) throws SQLException, ClassNotFoundException {

        ArrayList<Integer> couponsID = new ArrayList<Integer>();

        CouponDBDAO couponDBDAO = CouponDBDAO.getInstance();

        String sql = "SELECT COUPON_ID FROM customers_vs_coupons where CUSTOMER_ID=" + customerId;
        ResultSet resultset = dbm.runGetQuery(sql);

        while (resultset.next()) {
            int couponID = resultset.getInt("COUPON_ID");
            couponsID.add(couponID);
        }

        Coupon coupon;
        ArrayList coupons = new ArrayList<Coupon>();

        for (int couponId : couponsID) {
            coupon = couponDBDAO.getOneCoupon(couponId);
            coupons.add(coupon);
        }
        return coupons;
    }

    public ArrayList<Coupon> getCouponsByCategory(int customerId, int categoryId) throws SQLException, ClassNotFoundException {
        ArrayList<Integer> couponsID = new ArrayList<>();
        CouponDAO couponDAO = CouponDBDAO.getInstance();

        String sql = "SELECT COUPON_ID FROM customers_vs_coupons NATURAL JOIN coupons" +
                " WHERE CUSTOMER_ID=" + customerId + " and CATEGORY_ID=" + categoryId;
        ResultSet resultset = dbm.runGetQuery(sql);
        while (resultset.next()) {
            int couponID = resultset.getInt("COUPON_ID");
            couponsID.add(couponID);
        }
        ArrayList coupons = new ArrayList<Coupon>();
        for (int couponId : couponsID) {
            coupons.add(couponDAO.getOneCoupon(couponId));
        }
        return coupons;
    }

    @Override
    public ArrayList<Coupon> getCouponsByMaxPrice(int customerId, double maxPrice) throws SQLException, ClassNotFoundException {
        ArrayList<Integer> couponsID = new ArrayList<>();
        CouponDAO couponDAO = CouponDBDAO.getInstance();

        String sql = "SELECT COUPON_ID FROM customers_vs_coupons NATURAL JOIN coupons" +
                " WHERE CUSTOMER_ID=" + customerId + " and PRICE <= " + maxPrice;
        ResultSet resultset = dbm.runGetQuery(sql);
        while (resultset.next()) {
            int couponID = resultset.getInt("COUPON_ID");
            couponsID.add(couponID);
        }
        ArrayList coupons = new ArrayList<Coupon>();
        for (int couponId : couponsID) {
            coupons.add(couponDAO.getOneCoupon(couponId));
        }
        return coupons;
    }

    public Customer getOneCustomer(int id) {
        try {
            String sql = "SELECT * FROM customers where ID=" + id;
            ResultSet resultset = dbm.runGetQuery(sql);
            while (resultset.next()) {
                int idDB = resultset.getInt("ID");
                String firstName = resultset.getString("FIRST_NAME");
                String lastName = resultset.getString("LAST_NAME");
                String email = resultset.getString("EMAIL");
                String password = resultset.getString("PASSWORD");
                ArrayList<Coupon> coupons = getCouponsOfCustomer(id);

                Customer customer = new Customer(idDB, firstName, lastName, email, password, coupons);
                return customer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int add(Customer customer) throws SQLException {
        sql = "INSERT INTO customers VALUES (" + customer.getID() + ", '" + customer.getFirstName() +
                "', '" + customer.getLastName() + "', '" + customer.getEmail() +
                "', '" + customer.getPassword() + "')";

        System.out.println("ADD: " + customer);
//        int resultset = dbm.runUpdateQuery(sql);
        //check manually if id already exists?
        try {
            dbm.runUpdateQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
        //TODO what to return? 🤔
    }

    public void update(Customer customer) throws SQLException {
        String sql = "update customers set first_name = '" + customer.getFirstName() + "', last_name= '" +
                customer.getLastName() + "', email = '" + customer.getEmail() + "', password = '"
                + customer.getPassword() + "' where id =" + customer.getID();
        //        dbm.runUpdateQuery(sql);
        //
        if (idExists(customer.getID())) {
            dbm.runUpdateQuery(sql);
        } else {
            System.out.println("ID: " + customer.getID() + " not found!");
        }
    }

    @Override
    public void updateCouponOfCustomers(int customerId, int couponId) throws SQLException {
        String sql = "INSERT INTO customers_vs_coupons (CUSTOMER_ID, COUPON_ID) VALUES ('" + customerId + "', '" + couponId + "');";
        dbm.runUpdateQuery(sql);
    }


    @Override
    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> companiesList = new ArrayList<Customer>();
        try {
            String sql = "SELECT * FROM customers";
            ResultSet resultset = dbm.runGetQuery(sql);
            while (resultset.next()) {
                int idDB = resultset.getInt("id");
                String firstName = resultset.getString("first_name");
                String lastName = resultset.getString("last_name");
                String email = resultset.getString("email");
                String password = resultset.getString("password");
                companiesList.add(new Customer(idDB, firstName, lastName, email, password));
            }
            return companiesList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int isCustomerExists(Credentials credentials) {
        try {
            String sql = "select * from customers where email = '" + credentials.getEmail() + "' and password = '" + credentials.getPassword() + "'";
            ResultSet resultset = dbm.runGetQuery(sql);
            while (resultset.next()) {
                int idDB = resultset.getInt("id");
                return idDB;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    //aux func:
    public void deleteCouponPurchaseHistory(int customerID) throws SQLException {
        sql = "DELETE FROM customers_vs_coupons WHERE CUSTOMER_ID=" + customerID;
        int result = dbm.runUpdateQuery(sql);
        System.out.println("ID: " + customerID + " removed");
    }
}
