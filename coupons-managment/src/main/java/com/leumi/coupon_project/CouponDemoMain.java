package com.leumi.coupon_project;

import com.leumi.coupon_project.DAO.*;
import com.leumi.coupon_project.data.Company;
import com.leumi.coupon_project.data.Coupon;
import com.leumi.coupon_project.data.Customer;
import com.leumi.coupon_project.facade.AdminFacade;
import com.leumi.coupon_project.facade.CompanyFacade;

import java.sql.SQLException;

public class CouponDemoMain {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		//Encapsulation
		CompanyDAO companyDAO = CompanyDBDAO.getInstance();

		///////////////////////
		//test Company:

		Company company = new Company(128, "b", "y", "a");
//		companyDAO.add(company);  //v
//		System.out.println(company);


//		company = companyDAO.get(2);
//		System.out.println(company);
//		company = companyDAO.get(3);
//		System.out.println(company);

//		System.out.println(companyDAO.isCompanyExists("s", "a"));

		//test getAllCompanies:  V
//		ArrayList<Company> companies = companyDAO.getAllCompanies();
//		for (Company c :
//				companies) {
//			System.out.println(c);
//		}

//		System.out.println(companies);
//		companyDAO.getAllCompanies();


		//Update
		Company entityToAdd = new Company(1, "Leumi++", "as", "11");
//		companyDAO.update(entityToAdd); //v
		
		//Delete
//		companyDAO.remove(1); //v
//
//
//		//From the Main/App we use only the FACADE in order to update the database
//		AdminFacade adminFacade = new AdminFacade();
//		adminFacade.getOneCompany(2);  // doesnt show anything

////////////////////////////
// test Coupon:

		CouponDAO couponDAO = CouponDBDAO.getInstance();
		Coupon coupon1 = new Coupon(12, 1, Coupon.Category.Food, "first", 100);

		//test add:
//				couponDAO.add(coupon1); //v

		//test remove:
//		couponDAO.remove(1); //v

		//test update:
//		Coupon entityToAdd = new Coupon(1, 1, Coupon.Category.Restaurant, "first", 100.0);
//		couponDAO.update(entityToAdd); // v
		// test get:
		Coupon c = new Coupon();
//		c = couponDAO.get(1);
//		System.out.println(c); //v

		// test customer:
		CustomerDAO customerDAO = CustomerDBDAO.getInstance();

		// test add:
		Customer c1 = new Customer();
		c1.setID(1);
		c1.setFirstName("efrat");
		c1.setLastName("kenig");
		c1.setPassword("111");
		c1.setEmail("qqq");
//		customerDAO.add(c1); //v

		//test update:
		Customer customerToAdd = new Customer();
		customerToAdd.setID(1);
		customerToAdd.setFirstName("efrat1");
		customerToAdd.setLastName("kenig1");
//		customerDAO.update(customerToAdd); //v

		//test deleteCouponPurchaseHistory:
//		customerDAO.deleteCouponPurchaseHistory(1);

		//test adminFacade:
		AdminFacade a = new AdminFacade();
		a.getAllCompanies();
//		a.deleteCompany(1);

		CompanyFacade companyFacade = CompanyFacade.getInstance();
//		companyDAO.remove(1);
//		companyDAO.getAllCompanies();
//		couponDAO.remove(1);
//		companyFacade.deleteCoupon(1);
//		couponDAO.remove(1);
//		CustomerFacade customerFacade = CustomerFacade.getInstance();
//		customerFacade.getAllCustomerCoupons();
//		System.out.println(new java.sql.Date(new Date(System.currentTimeMillis()).getTime()));
		Coupon coupon2 = new Coupon(178, 1, Coupon.Category.Food, "String title", 90.0);
//		couponDAO.add(coupon2);


//		System.out.println(companyFacade.getCompanyCouponsByCompanyID(1));
	}

}
