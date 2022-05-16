package com.leumi.coupon_project.facade;

import com.leumi.coupon_project.data.Company;
import com.leumi.coupon_project.data.Customer;
import com.leumi.coupon_project.helpers.Credentials;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Objects;

public class AdminFacade extends ClientFacade {
    private static AdminFacade instance;
    public AdminFacade() throws SQLException, ClassNotFoundException {
        super();
    }

    public static AdminFacade getInstance() throws SQLException, ClassNotFoundException {
        if (instance==null) {
            instance = new AdminFacade();
        }
        return instance;
    }

    @Override
    public boolean login(Credentials cred){
        return Objects.equals(cred.getEmail(), "admin@admin.com") && Objects.equals(cred.getPassword(), "admin");
    }

    public void addCompany(Company company) throws SQLException{
        try{
            companyDAO.add(company);
        }catch (SQLIntegrityConstraintViolationException e){
            System.out.println("Error adding company: company name or email already exist");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCompany(Company company) throws SQLException {
        companyDAO.update(company);
    }

    public void deleteCompany(int companyID) throws SQLException {
        companyDAO.remove(companyID);
        //for now, automatically removes coupons and
        // coupon purchases because defined in database as 'cascade'.
    }

    public ArrayList<Company> getAllCompanies() throws SQLException {
        return companyDAO.getAllCompanies();
    }

    public Company getOneCompany(int companyID) throws SQLException{
        return companyDAO.getOneCompany(companyID);
    }

    public void addCustomer(Customer customer) throws SQLException{
        try{
            customerDAO.add(customer);
        }catch (SQLIntegrityConstraintViolationException e){
            System.out.println("Error adding customer: customer email already exist");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCustomer(Customer customer) throws SQLException {
        customerDAO.update(customer);
    }

    public void deleteCustomer(int customerID) throws SQLException {
        customerDAO.deleteCouponPurchaseHistory(customerID);
        customerDAO.remove(customerID);
    }

    public ArrayList<Customer> getAllCustomers()throws SQLException{
        return customerDAO.getAllCustomers();
    }

    public Customer getOneCustomer(int customerID)throws SQLException{
        return customerDAO.getOneCustomer(customerID);
    }
}
