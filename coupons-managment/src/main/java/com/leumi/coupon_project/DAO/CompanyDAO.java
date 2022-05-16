package com.leumi.coupon_project.DAO;

import com.leumi.coupon_project.data.Company;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CompanyDAO {

	//CRUD functions

	int add(Company entity) throws SQLException;
	void remove(int id) throws SQLException;

    boolean validateLoginInfo(int companyId, String email, String password);

	Company getCompanyDetails(int companyID);
    Company getOneCompany(int id);
	void update(Company entity) throws SQLException;
	ArrayList<Company> getAllCompanies();
	int isCompanyExists(String email, String password) throws SQLException;
}
