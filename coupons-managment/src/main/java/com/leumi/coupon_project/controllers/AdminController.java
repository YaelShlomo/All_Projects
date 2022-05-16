package com.leumi.coupon_project.controllers;

import com.leumi.coupon_project.data.Company;
import com.leumi.coupon_project.data.Customer;
import com.leumi.coupon_project.facade.AdminFacade;
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
@RequestMapping("/admin")
public class AdminController extends ClientController{


    @Autowired
    SimpleTokenManager simpleTokenManager;

    AdminFacade adminFacade = AdminFacade.getInstance();

    public AdminController() throws SQLException, ClassNotFoundException {
        super();
    }

    @Override
    @PostMapping("/admin/login")
    public ResponseEntity<?> login(@RequestBody Credentials cred)
    {
        System.out.println(new Date()+": Got a new login: " + cred);
        if (adminFacade.login(cred)) {
            String token = simpleTokenManager.getNewToken();
            return new ResponseEntity<String>(token, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Login error!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping ("/addCompany")
    public ResponseEntity<?> addCompany(@RequestBody Company company, @RequestHeader("token") String token) throws SQLException {
        try{
            //check if the provided token is valid:
            if(simpleTokenManager.isTokenExist(token)){
                adminFacade.addCompany(company);
            }else{
                return new ResponseEntity<String>("Invalid Token provided", HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            return new ResponseEntity<String>("Error adding company", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("company added successfully", HttpStatus.OK);
    }

    @PutMapping ("/updateCompany")
    public ResponseEntity<?> updateCustomer(@RequestBody Company company, @RequestHeader("token") String token) throws SQLException {
        try{
            //check if the provided token is valid:
            if(simpleTokenManager.isTokenExist(token)){
                adminFacade.updateCompany(company);
            }else{
                return new ResponseEntity<String>("Invalid Token provided", HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            return new ResponseEntity<String>("Error updating company", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("company updated successfully", HttpStatus.OK);
    }

    @DeleteMapping ("/deleteCompany")
    public ResponseEntity<?> deleteCompany(@RequestParam int companyID, @RequestHeader("token") String token) throws SQLException {
        try{
            //check if the provided token is valid:
            if(simpleTokenManager.isTokenExist(token)){
                adminFacade.deleteCompany(companyID);
            }else{
                return new ResponseEntity<String>("Invalid Token provided", HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            return new ResponseEntity<String>("error deleting company", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("company was deleted successfully", HttpStatus.OK);
    }

    @GetMapping ("/getAllCompanies")
    public ResponseEntity<?> getAllCompanies(@RequestHeader("token") String token) throws SQLException {
        try{
            //check if the provided token is valid:
            if(simpleTokenManager.isTokenExist(token)){
                return new ResponseEntity<ArrayList<Company>>(adminFacade.getAllCompanies(), HttpStatus.OK);
            }else{
                return new ResponseEntity<String>("Invalid Token provided", HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            return new ResponseEntity<String>("Error getting all companies", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping ("/getOneCompany")
    public ResponseEntity<?> getOneCompany(@RequestParam int companyID, @RequestHeader("token") String token) throws SQLException {
        try{
            //check if the provided token is valid:
            if(simpleTokenManager.isTokenExist(token)){
                return new ResponseEntity<Company>(adminFacade.getOneCompany(companyID), HttpStatus.OK);
            }else{
                return new ResponseEntity<String>("Invalid Token provided", HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            return new ResponseEntity<String>("Error getting one company", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping ("/addCustomer")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer, @RequestHeader("token") String token) throws SQLException {
        try{
            //check if the provided token is valid:
            if(simpleTokenManager.isTokenExist(token)){
                adminFacade.addCustomer(customer);
            }else{
                return new ResponseEntity<String>("Invalid Token provided", HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            return new ResponseEntity<String>("Error adding customer", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("customer added successfully", HttpStatus.OK);
    }

    @PutMapping ("/updateCustomer")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @RequestHeader("token") String token) throws SQLException {
        try{
            //check if the provided token is valid:
            if(simpleTokenManager.isTokenExist(token)){
                adminFacade.updateCustomer(customer);
            }else{
                return new ResponseEntity<String>("Invalid Token provided", HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            return new ResponseEntity<String>("Error updating customer", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("customer updated successfully", HttpStatus.OK);
    }

    @DeleteMapping ("/deleteCustomer")
    public ResponseEntity<?> deleteCustomer(@RequestParam int customerID, @RequestHeader("token") String token) throws SQLException {
        try{
            //check if the provided token is valid:
            if(simpleTokenManager.isTokenExist(token)){
                adminFacade.deleteCustomer(customerID);
            }else{
                return new ResponseEntity<String>("Invalid Token provided", HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            return new ResponseEntity<String>("error deleting customer", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("customer was deleted successfully", HttpStatus.OK);
    }



    @GetMapping ("/getAllCustomers")
    public ResponseEntity<?> getAllCustomers(@RequestHeader("token") String token) throws SQLException {
        try{
            //check if the provided token is valid:
            if(simpleTokenManager.isTokenExist(token)){
                return new ResponseEntity<ArrayList<Customer>>(adminFacade.getAllCustomers(), HttpStatus.OK);
            }else{
                return new ResponseEntity<String>("Invalid Token provided", HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            return new ResponseEntity<String>("Error getting all customers", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping ("/getOneCustomer")
    public ResponseEntity<?> getOneCustomer(@RequestParam int customerID, @RequestHeader("token") String token) throws SQLException {
        try{
            //check if the provided token is valid:
            if(simpleTokenManager.isTokenExist(token)){
                return new ResponseEntity<Customer>(adminFacade.getOneCustomer(customerID), HttpStatus.OK);
            }else{
                return new ResponseEntity<String>("Invalid Token provided", HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            return new ResponseEntity<String>("Error getting one customer", HttpStatus.BAD_REQUEST);
        }
    }

}