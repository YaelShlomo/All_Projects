package com.leumi.coupon_project.test;

import com.leumi.coupon_project.data.Company;
import com.leumi.coupon_project.data.Coupon;
import com.leumi.coupon_project.data.Customer;
import com.leumi.coupon_project.helpers.CouponExpirationDailyJob;
import com.leumi.coupon_project.helpers.LoginManager;
import com.leumi.coupon_project.facade.AdminFacade;
import com.leumi.coupon_project.facade.CompanyFacade;
import com.leumi.coupon_project.facade.CustomerFacade;
import com.leumi.coupon_project.helpers.Credentials;

public class Test {
    public static void testAll() throws Exception {
        //run daily job:
        CouponExpirationDailyJob job = new CouponExpirationDailyJob();
        job.run();

        LoginManager loginManager = LoginManager.getInstance();
        Credentials adminCredentials = new Credentials("admin@admin.com", "admin", LoginManager.ClientType.Administrator);
        Credentials companyCredentials = new Credentials("e", "e", LoginManager.ClientType.Company);
        Credentials customerCredentials = new Credentials("y", "k", LoginManager.ClientType.Customer);

        //log in
        AdminFacade adminFacade = (AdminFacade) loginManager.Login(adminCredentials);
        CompanyFacade companyFacade = (CompanyFacade) loginManager.Login(companyCredentials);
        CustomerFacade customerFacade = (CustomerFacade) loginManager.Login(customerCredentials);

        Company company1 = new Company(28, "F", "F@f", "mysterious");
        Company company1Updated = new Company(28, "F", "F@f", "notMysterious");
        Customer customer1 = new Customer(23, "Ed", "D", "ed@d", "rfgdjh");
        Customer customer1Updated = new Customer(23, "Ed", "D", "ed@d", "norfgdjh");
        Coupon coupon1 = new Coupon(33,1, Coupon.Category.Electricity, "title", 10);
        Coupon coupon1Updated = coupon1;
        coupon1Updated.setAmount(12);

        //run admin facade methods:
        System.out.println(adminFacade.getAllCompanies());
        System.out.println(adminFacade.getAllCustomers());
        System.out.println(adminFacade.getOneCompany(1));
        System.out.println(adminFacade.getOneCustomer(1));
        adminFacade.addCompany(company1);
        adminFacade.addCustomer(customer1);
        adminFacade.updateCompany(company1Updated);
        adminFacade.updateCustomer(customer1Updated);
        adminFacade.deleteCompany(28);
        adminFacade.deleteCustomer(23);

        //run company facade methods:
        companyFacade.getCompanyCouponsByCompanyID();
        System.out.println(companyFacade.getCompanyCouponsByCompanyIDCategory(Coupon.Category.Food));
        System.out.println(companyFacade.getCompanyCouponsByCompanyIDPrice( 100.0));
        System.out.println(companyFacade.getCompanyDetails());
        companyFacade.addCoupon(coupon1);
        companyFacade.updateCoupon(coupon1Updated);
        companyFacade.deleteCoupon(33);

        //run company facade methods:
        customerFacade.purchaseCoupon(coupon1Updated);
        System.out.println(customerFacade.getCustomerCouponsByMaxPrice(100));
        System.out.println(customerFacade.getCustomerCouponsByCategory(Coupon.Category.Food));
        System.out.println(customerFacade.getCustomerCoupons());
        System.out.println(customerFacade.getCustomerDetails());
    }
}
