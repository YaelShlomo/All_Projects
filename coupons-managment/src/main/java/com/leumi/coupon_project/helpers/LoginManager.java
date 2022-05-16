package com.leumi.coupon_project.helpers;

import com.leumi.coupon_project.facade.AdminFacade;
import com.leumi.coupon_project.facade.ClientFacade;
import com.leumi.coupon_project.facade.CompanyFacade;
import com.leumi.coupon_project.facade.CustomerFacade;

import java.sql.SQLException;

public class LoginManager {

    public enum ClientType {
        Administrator,
        Company,
        Customer;
    }

    private static LoginManager instance;

    public static LoginManager getInstance() {
        if (instance == null) {
            try {
                instance = new LoginManager();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public ClientFacade Login(Credentials credentials) throws SQLException, ClassNotFoundException {
        ClientFacade facade = CompanyFacade.getInstance();
        switch (credentials.getClientType()) {
            case Company:
                facade = CompanyFacade.getInstance();
                break;
            case Customer:
                facade = CustomerFacade.getInstance();
                break;
            case Administrator:
                facade = AdminFacade.getInstance();
                break;

//                //TODO: what to do as default?
//            default:
//                facade = CustomerFacade.getInstance();
        }

        if (facade.login(credentials)) {
            System.out.println("logged in successfully");
            return facade;
        }
        return null;
    }

}