package com.leumi.coupon_project.helpers;

import com.leumi.coupon_project.data.Coupon;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//import com.example.coupon_system.entities.Coupon;

@Component
@Scope("singleton")
public class TestRepository {
	private static final int INITIAL_COUPONS_NUMBER = 10;
	List<Coupon> allCoupons = new ArrayList<Coupon>();
	List<Credentials> validUsers = new ArrayList<Credentials>();

	public TestRepository() {
		super();
		//Initializing a demo credentials
		validUsers.add(new Credentials("r@a", "123", LoginManager.ClientType.Administrator));
		validUsers.add(new Credentials("r@b", "123", LoginManager.ClientType.Company));
		validUsers.add(new Credentials("r@c", "123", LoginManager.ClientType.Customer));
		
		//Initializing a demo coupons
//		for (int i = 0; i < INITIAL_COUPONS_NUMBER; i++) {
//			Coupon coupon = new Coupon();
//			coupon.setID(i);
//			allCoupons.add(new Coupon(i, "Coupon_"+i, "This is a discription for coupon "+i,""+ new Date()));
//		}
	}

	public List<Coupon> getCoupons()
	{
		return allCoupons;
	}

	public Integer addCoupon(Coupon coupon) {
		coupon.setID(allCoupons.size());
		allCoupons.add(coupon);
		return coupon.getID();
	}
//
//	public boolean isValidCredentials(Credentials cred) throws SQLException, ClassNotFoundException {
//		if (Objects.equals(cred.getRole(), "company")) {
//			CompanyFacade companyFacade = CompanyFacade.getInstance();
//			return companyFacade.login(cred.getEmail(), cred.getPassword());
//		}
//	}
}
