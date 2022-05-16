package com.leumi.coupon_project.controllers;

import com.leumi.coupon_project.data.Coupon;
import com.leumi.coupon_project.data.Customer;
import com.leumi.coupon_project.helpers.LoginManager;
import com.leumi.coupon_project.facade.CustomerFacade;
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
@RequestMapping("/customer")
public class CustomerController extends ClientController {

    @Autowired
    SimpleTokenManager simpleTokenManager;
    CustomerFacade customerFacade;

    public CustomerController() {
        super();
    }

    /**
     * A successful login will return a valid token to the client for future requests.
     * this token will be valid for SimpleTokenManager.EXPIRATION_TIME_PERIOD_IN_MILLIS, after
     * this period passes SimpleTokenManager.removeExpiredSessions() will remove this token and
     * it'll be no longer a valid token.
     *
     * @param cred
     *
     */
    @Override
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody Credentials cred) {
        System.out.println(new Date() + ": Got a new login: " + cred);
        try {
            customerFacade = (CustomerFacade) LoginManager.getInstance().Login(cred);
            if (customerFacade != null) {
                String token = simpleTokenManager.getNewToken();
                return new ResponseEntity<>(token, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Login error!", HttpStatus.BAD_REQUEST);
            }
        } catch (SQLException | ClassNotFoundException e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/purchaseCoupon")
    public ResponseEntity<String> purchaseCoupon(@RequestBody Coupon coupon, @RequestHeader("token") String token) {
        try {
            customerFacade.purchaseCoupon(coupon);
            if (simpleTokenManager.isTokenExist(token)) {
                return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity<String>("success", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCustomerCoupons")
    public ResponseEntity<?> getCustomerCoupons(@RequestHeader("token") String token) {
        try {
            if (simpleTokenManager.isTokenExist(token)) {
                return new ResponseEntity<ArrayList<Coupon>>(customerFacade.getCustomerCoupons(), HttpStatus.OK);
            }else{
                return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCustomerCouponsByCategory")
    public ResponseEntity<?> getCustomerCoupons(@RequestParam Coupon.Category category, @RequestHeader("token") String token) {
        try {
            if (simpleTokenManager.isTokenExist(token)) {
                return new ResponseEntity<ArrayList<Coupon>>(customerFacade.getCustomerCouponsByCategory(category), HttpStatus.OK);
            }else{
                return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCustomerCouponsByMaxPrice")
    public ResponseEntity<?> getCustomerCoupons(@RequestParam double maxPrice, @RequestHeader("token") String token) {
        try {
            if (simpleTokenManager.isTokenExist(token)) {
                return new ResponseEntity<ArrayList<Coupon>>(customerFacade.getCustomerCouponsByMaxPrice(maxPrice), HttpStatus.OK);
            }
            else{return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);}
        } catch (Exception e) {
            return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCustomerDetails")
    public ResponseEntity<?> getCustomerDetails(@RequestHeader("token") String token) {
        try {
            if (simpleTokenManager.isTokenExist(token)) {
                return new ResponseEntity<Customer>(customerFacade.getCustomerDetails(), HttpStatus.OK);
            }
            else{return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);}
        } catch (Exception e) {
            return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
