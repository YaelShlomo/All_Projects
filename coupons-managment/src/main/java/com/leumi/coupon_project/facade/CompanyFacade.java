package com.leumi.coupon_project.facade;

import com.leumi.coupon_project.data.Company;
import com.leumi.coupon_project.data.Coupon;
import com.leumi.coupon_project.helpers.Credentials;

import java.sql.SQLException;
import java.util.ArrayList;

public class CompanyFacade extends ClientFacade {

    private static CompanyFacade instance;
    private int companyID;

    private CompanyFacade() throws SQLException, ClassNotFoundException {
        super();
    }

    public static CompanyFacade getInstance() throws SQLException, ClassNotFoundException {
        if (instance==null) {
            instance = new CompanyFacade();
        }
        return instance;
    }

    @Override
    public boolean login(Credentials credentials) throws SQLException {
        int id = companyDAO.isCompanyExists(credentials.getEmail(), credentials.getPassword());
        if (id == -1) {
            return false;
        }
        companyID = id;
        return true;
    }

    public int addCoupon(Coupon coupon) throws Exception {
        System.out.println("\n\n"+couponDAO.isCouponWithCompanyIdAndTitleExist(coupon.getCompanyID(), coupon.getTitle())+"\n\n");
        if (!couponDAO.isCouponWithCompanyIdAndTitleExist(coupon.getCompanyID(), coupon.getTitle())) {
            System.out.println("adding coupon id = "+coupon.getID());
            return couponDAO.add(coupon);
        }
        throw new Exception("coupon is already exist in company");
    }

    public void updateCoupon(Coupon coupon) throws SQLException {
        couponDAO.update(coupon);
    }

    public void deleteCoupon(int id) throws SQLException {
        couponDAO.remove(id);
    }

    public ArrayList<Coupon> getCompanyCouponsByCompanyID() {
        return couponDAO.getCompanyCouponsByCompanyID(companyID);
    }

    public ArrayList<Coupon> getCompanyCouponsByCompanyIDCategory(Coupon.Category category) {
        return couponDAO.getCompanyCouponsByCompanyIDCategory(companyID, category);
    }

    public ArrayList<Coupon> getCompanyCouponsByCompanyIDPrice(Double maxPrice) {
        return couponDAO.getCompanyCouponsByCompanyIDPrice(companyID, maxPrice);
    }

    public Company getCompanyDetails() {
        return companyDAO.getCompanyDetails(companyID);
    }
}