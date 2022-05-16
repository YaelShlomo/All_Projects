package com.leumi.coupon_project.facade;

import com.leumi.coupon_project.data.Coupon;
import com.leumi.coupon_project.data.Customer;
import com.leumi.coupon_project.helpers.Credentials;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CustomerFacade extends ClientFacade {

    private static CustomerFacade instance;
    private int customerID;

    private CustomerFacade() throws SQLException, ClassNotFoundException {
        super();
    }

    public static CustomerFacade getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) instance = new CustomerFacade();
        return instance;
    }

    @Override
    public boolean login(Credentials credentials) {
        int id = customerDAO.isCustomerExists(credentials);
        if (id == -1) {
            return false;
        }
        customerID = id;
        return true;
    }

    public void purchaseCoupon(Coupon coupon) throws Exception {

        int id = coupon.getID();
        try{
            Coupon dbCoupon = couponDAO.getOneCoupon(id);


            for (Coupon c : customerDAO.getCouponsOfCustomer(customerID)) {
                if (c.getID() == id) {
                    throw new Exception("Cannot buy coupon more than one time");
                }
            }

            if (dbCoupon.getAmount() == 0) {
                throw new Exception("Out of stock");
            }
            if (coupon.getEndDate().before(new Date())) {
                throw new Exception("Expiration date of the coupon has passed");
            }
            customerDAO.updateCouponOfCustomers(customerID, coupon.getID());

            dbCoupon.setAmount(dbCoupon.getAmount() - 1);
            couponDAO.update(dbCoupon);
        }catch (NullPointerException exception){
            System.out.println("coupon: "+ id +"does not exist");
        }
    }

    public ArrayList<Coupon> getCustomerCoupons() throws SQLException, ClassNotFoundException {
        return customerDAO.getCouponsOfCustomer(customerID);
    }

    public ArrayList<Coupon> getCustomerCouponsByCategory(Coupon.Category category) throws SQLException, ClassNotFoundException {
        return customerDAO.getCouponsByCategory(customerID, category.ordinal());
    }

    public ArrayList<Coupon> getCustomerCouponsByMaxPrice(double maxPrice) throws SQLException, ClassNotFoundException {
        return customerDAO.getCouponsByMaxPrice(customerID, maxPrice);
    }

    public Customer getCustomerDetails() {
        return customerDAO.getOneCustomer(customerID);
    }
}
