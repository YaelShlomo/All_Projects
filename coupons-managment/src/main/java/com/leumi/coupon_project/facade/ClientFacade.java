package com.leumi.coupon_project.facade;

import com.leumi.coupon_project.DAO.*;
import com.leumi.coupon_project.helpers.Credentials;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service

public abstract class ClientFacade {

    CompanyDAO companyDAO;
    CustomerDAO customerDAO;
    CouponDAO couponDAO;

    protected ClientFacade() throws SQLException, ClassNotFoundException {
        companyDAO = CompanyDBDAO.getInstance();
        customerDAO = CustomerDBDAO.getInstance();
        couponDAO = CouponDBDAO.getInstance();
    }

    public boolean login(Credentials cred) throws SQLException {
        return false;
    }
}
