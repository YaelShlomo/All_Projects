package com.leumi.coupon_project.controllers;

import com.leumi.coupon_project.data.Company;
import com.leumi.coupon_project.data.Coupon;
import com.leumi.coupon_project.facade.CompanyFacade;
import com.leumi.coupon_project.helpers.Credentials;
import com.leumi.coupon_project.helpers.SimpleTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("company")
public class CompanyController extends ClientController {

    CompanyFacade companyFacade = CompanyFacade.getInstance();

    @Autowired
    SimpleTokenManager simpleTokenManager;

    public CompanyController() throws SQLException, ClassNotFoundException {
    }

    @Override
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody Credentials cred) throws SQLException {
        System.out.println(new Date()+": Got a new login: "+cred);
        if (companyFacade.login(cred)) {
            String token = simpleTokenManager.getNewToken();
            return new ResponseEntity<String>(token, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Login error!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("addCoupon")
    public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon, @RequestHeader("token") String token) {
        System.out.println("Got coupon " + coupon);
        try {
            if (simpleTokenManager.isTokenExist(token)) {
                companyFacade.addCoupon(coupon);
                int id = coupon.getID();
                System.out.println("adding coupon id: "+ id);
                return new ResponseEntity<Integer>(id, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Token is not valid!!", HttpStatus.BAD_GATEWAY);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>("Error add coupon!!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateCoupon")
    public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon, @RequestHeader("token") String token) {
        System.out.println("Got coupon to update:" + coupon);
        try {
            if (simpleTokenManager.isTokenExist(token)) {
                companyFacade.updateCoupon(coupon);
                return new ResponseEntity<Integer>(HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Token is not valid!!", HttpStatus.BAD_GATEWAY);
            }
        } catch (SQLException e) {
            return new ResponseEntity<String>("Error update coupon!!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("deleteCoupon")
    public ResponseEntity<?> deleteCoupon(@RequestParam int id, @RequestHeader("token") String token) {
        System.out.println("Got coupon to delete: " + id);
        try {
            if (simpleTokenManager.isTokenExist(token)) {
                companyFacade.deleteCoupon(id);
                return new ResponseEntity<Integer>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<String>("Token is not valid!!", HttpStatus.BAD_GATEWAY);
            }
        } catch (SQLException e) {
            return new ResponseEntity<String>("Error delete coupon!!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getCompanyCouponsByCompanyID")
    @ResponseBody
    public ResponseEntity<?> getCompanyCouponsByCompanyID(@RequestHeader("token") String token) {
        System.out.println("Got a request getCompanyCoupons from client!");
        if (simpleTokenManager.isTokenExist(token)) {
            ArrayList<Coupon> res = companyFacade.getCompanyCouponsByCompanyID();
            return new ResponseEntity<ArrayList<Coupon>>(res, HttpStatus.OK);
        }
        return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getCompanyCouponsByCompanyIDCategory/{category}")
    @ResponseBody
    public ResponseEntity<?> getCompanyCouponsByCompanyIDCategory(@PathVariable Coupon.Category category, @RequestHeader("token") String token) {
        System.out.println("Got a request getCompanyCoupons by category from client!");
        if (simpleTokenManager.isTokenExist(token)) {
            ArrayList<Coupon> res = companyFacade.getCompanyCouponsByCompanyIDCategory(category);
            return new ResponseEntity<ArrayList<Coupon>>(res, HttpStatus.OK);
        }
        return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getCompanyCouponsByCompanyIDPrice/{maxPrice}")
    @ResponseBody
    public ResponseEntity<?> getCompanyCouponsByCompanyIDPrice(@PathVariable Double maxPrice, @RequestHeader("token") String token) {
        System.out.println("Got a request getCompanyCoupons by maxPrice from client!");
        if (simpleTokenManager.isTokenExist(token)) {
            ArrayList<Coupon> res = companyFacade.getCompanyCouponsByCompanyIDPrice(maxPrice);
            return new ResponseEntity<ArrayList<Coupon>>(res, HttpStatus.OK);
        }
        return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getCompanyDetails/{token}")
    @ResponseBody
    public ResponseEntity<?> getCompanyDetails(@RequestHeader("token") String token) {
        System.out.println("Got a request getCompanyCoupons by category from client!");
        if (simpleTokenManager.isTokenExist(token)) {
            Company company = companyFacade.getCompanyDetails();
            return new ResponseEntity<Company>(company, HttpStatus.OK);
        }
        return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
    }

}
