package com.leumi.coupon_project.DAO;

import com.leumi.coupon_project.data.Company;
import com.leumi.coupon_project.data.DataBaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompanyDBDAO implements CompanyDAO {

	private static CompanyDBDAO instance;
//	private String sql;

	private CompanyDBDAO() throws SQLException, ClassNotFoundException {
		super();
	}

	public static CompanyDBDAO getInstance() throws SQLException, ClassNotFoundException {
		if (instance==null) {
			instance = new CompanyDBDAO();
		}
		return instance;
	}

	// aux func:
	public boolean idExists(int id){
		return this.getOneCompany(id) != null;
	}
	DataBaseManager dbm = DataBaseManager.getInstance();

	public int add(Company entity) throws SQLException {
		int res = -1;
		String sql = "INSERT INTO companies VALUES ("+entity.getID()+", '"+entity.getName()+"', '"+ entity.getEmail()+"', '"+ entity.getPassword() +"')";
		try{
			if(isCompanyExists(entity.getEmail(), entity.getPassword())!=-1){
				System.out.println("company "+ entity +" already exists");
				return res;
			}
			res = dbm.runUpdateQuery(sql);
		}catch (Exception e){return 1;}
		return res;
	}

	public void remove(int id) throws SQLException {
		String sql = "delete from companies where id="+id;
		if(idExists(id)){
			dbm.runUpdateQuery(sql);
		}
		else {
			System.out.println("ID: "+ id+" not found!");
		}
	}

	public void update(Company entity) throws SQLException {
		String sql = "update companies set email ='"+ entity.getEmail()+ "', password ='"+
				entity.getPassword()+"' where id ="+entity.getID();
		System.out.println("UPDATE: "+entity);
		if(idExists(entity.getID())){
			dbm.runUpdateQuery(sql);
		}
		else {
			System.out.println("ID: "+ entity.getID()+" not found!");
		}
	}

	@Override
	public ArrayList<Company> getAllCompanies() {
		ArrayList<Company> companiesList = new ArrayList<Company>();
		try {
			String sql = "SELECT * FROM companies";
			ResultSet resultset = dbm.runGetQuery(sql);
			while (resultset.next()) {
				int idDB = resultset.getInt("id");
				String name = resultset.getString("name");
				String email = resultset.getString("email");
				String password = resultset.getString("password");
				companiesList.add(new Company(idDB, name, email, password));
			}
			return companiesList;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int isCompanyExists(String email, String password) throws SQLException {
		String sql = "select * from companies where email = '" + email + "' and password = '" + password + "'";
		ResultSet resultset = dbm.runGetQuery(sql);
		while (resultset.next()) {
			int idDB = resultset.getInt("id");
			return idDB;
		}
		return -1;
	}

	@Override
	public boolean validateLoginInfo(int companyId, String email, String password) {
		try {
			String sql = "select * from companies where id="+companyId+" email = '"+email+"' and password = '"+ password +"'";
			ResultSet resultset = dbm.runGetQuery(sql);
			while (resultset.next()) {
				int idDB = resultset.getInt("id");

				return true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Company getOneCompany(int id) {
		try {
			String sql = "SELECT * FROM companies where id="+id;
			ResultSet resultset = dbm.runGetQuery(sql);
			while (resultset.next()) {
				int idDB = resultset.getInt("id");
				String name = resultset.getString("name");
				String email = resultset.getString("email");
				String password = resultset.getString("password");
//				System.out.println(new Company(idDB, name, email, password));
				Company newCompany = new Company(idDB, name, email, password);
				return newCompany;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Company getCompanyDetails(int companyID) {
		return getOneCompany(companyID);
	}
	//TODO methods should be overridden?


}
