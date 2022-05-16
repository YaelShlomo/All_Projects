package com.leumi.coupon_project.DAO;

import com.leumi.coupon_project.data.Coupon;
import com.leumi.coupon_project.data.DataBaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CouponDBDAO implements CouponDAO {

    private static CouponDBDAO instance;
//    private String sql;

    public CouponDBDAO() throws SQLException, ClassNotFoundException {
        super();
    }

    public static CouponDBDAO getInstance() throws SQLException, ClassNotFoundException {
        if (instance==null) {
            instance = new CouponDBDAO();
        }
        return instance;
    }
    DataBaseManager dbm = DataBaseManager.getInstance();

    public boolean idExists(int id){
        return this.getOneCoupon(id) != null;
    }

    public int add(Coupon coupon) throws SQLException {
        // find the category id in categories chart in the database:
        String categoryName = coupon.getCategory().name();
        int categoryID = findCategoryIDByName(categoryName);

        java.sql.Date sqlStartDate = new java.sql.Date(coupon.getStartDate().getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(coupon.getEndDate().getTime());
        String sql="INSERT INTO coupons VALUES ("+coupon.getID()+", "+coupon.getCompanyID()+", "+categoryID+ ", '"+coupon.getTitle()+"', '"
                +coupon.getDescription() +"', DATE '"+sqlStartDate+ "', DATE '" +sqlEndDate+ "', "
                +coupon.getAmount()+", "+ coupon.getPrice()+", '"+coupon.getImage()+"')";

        System.out.println("ADD: "+coupon);
//        int resultset = dbm.runUpdateQuery(sql);
        //check manually if id already exists?
        try{
            dbm.runUpdateQuery(sql);
            return coupon.getID();
        }catch (Exception e){
            e.printStackTrace();
            }
        return 0;
    }

    public void remove(int id) throws SQLException {
        String sql="DELETE FROM coupons WHERE id="+id;
        int result = dbm.runUpdateQuery(sql);
        System.out.println("ID: "+id+" removed");
    }

    public void update(Coupon coupon) throws SQLException {
        String categoryName = coupon.getCategory().name();
        int categoryID = findCategoryIDByName(categoryName);
        java.sql.Date sqlStartDate = new java.sql.Date(coupon.getStartDate().getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(coupon.getEndDate().getTime());
        String sql="update coupons set company_id = "+coupon.getCompanyID() +", category_id="+categoryID+
                ", title= '"+coupon.getTitle()+"', description = '"
                +coupon.getDescription()+ "', start_date = DATE '"+sqlStartDate+
                "',end_date = DATE '" +sqlEndDate+ "', amount="+coupon.getAmount()+
                ", price = "+ coupon.getPrice()+", image = '"+coupon.getImage()+
                "' where id ="+coupon.getID();
        if(idExists(coupon.getID())){
            dbm.runUpdateQuery(sql);
        }
        else {
            System.out.println("ID: "+ coupon.getID()+" not found!");
        }
    }

    Coupon.Category findCategoryById(int categoryID) throws SQLException {
        String sql = "SELECT name from categories WHERE id="+categoryID;
        ResultSet resultset = dbm.runGetQuery(sql);
        while (resultset.next()) {
            String categoryName = resultset.getString("name");
            Coupon.Category category = Coupon.Category.valueOf(categoryName);
            return category;
        }
        return Coupon.Category.values()[0];
    }

    int findCategoryIDByName(String categoryName) throws SQLException {
        String sql = "SELECT id from categories WHERE name="+"'"+categoryName+"'";
        ResultSet resultset = dbm.runGetQuery(sql);
        while (resultset.next()) {
            int categoryID = resultset.getInt("id");
//            Coupon.Category category = Coupon.Category.valueOf(categoryName);
            return categoryID;
        }
        return 0;
    }

    public Coupon getOneCoupon(int id) {
        try {
            String sql = "SELECT * FROM coupons where id="+id;
            ResultSet resultset = dbm.runGetQuery(sql);
            while (resultset.next()) {
                int idDB = resultset.getInt("id");
                int companyID = resultset.getInt("company_id");
                int categoryID = resultset.getInt("category_id");
                Coupon.Category category = findCategoryById(categoryID);
                String title = resultset.getString("title");
                String description = resultset.getString("description");
                Date startDate = resultset.getDate("start_date");
                Date endDate = resultset.getDate("end_date");
                int amount = resultset.getInt("amount");
                int price = resultset.getInt("price");
                String image = resultset.getString("image");
                return new Coupon(idDB, companyID, category, title, description,startDate, endDate, amount, price, image);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //aux fanc':
    public void deleteCompanyCoupons(int companyID) throws SQLException {
        String sql="DELETE FROM coupons WHERE company_id="+companyID;
        int result = dbm.runUpdateQuery(sql);
        System.out.println("coupon of company: "+companyID+" removed");
    }

    //for use in CouponExpirationDailyJob class
    public void deleteExpiredCoupons(){
        //
    }

    //yaels func for company facade:
    public boolean isCouponWithCompanyIdAndTitleExist(int companyId, String title) throws SQLException {
        String sql = "SELECT * from coupons WHERE COMPANY_ID="+companyId+" AND TITLE='"+title+"'";
        ResultSet resultset = dbm.runGetQuery(sql);
        while (resultset.next()) {
            System.out.println("//////////////////////////////exists");
            int categoryID = resultset.getInt("id");
            return true;
        }
        return false;
    }
    //yaels func for company facade:

    @Override
    public ArrayList<Coupon> getCompanyCouponsByCompanyIDPrice(int companyId, Double price) {
        ArrayList<Coupon> coupons = new ArrayList<>();
        try {
            String sql = "SELECT * FROM coupons WHERE COMPANY_ID="+companyId+" AND PRICE<="+price;
            ResultSet resultset = dbm.runGetQuery(sql);
            while (resultset.next()) {
                int idDB = resultset.getInt("id");
                int companyID = resultset.getInt("company_id");
                int categoryID = resultset.getInt("category_id");
                Coupon.Category category = findCategoryById(categoryID);
                String title = resultset.getString("title");
                String description = resultset.getString("description");
                Date startDate = resultset.getDate("start_date");
                Date endDate = resultset.getDate("end_date");
                int amount = resultset.getInt("amount");
                String image = resultset.getString("image");
                Coupon coupon = new Coupon(idDB, companyID, category, title, description,startDate, endDate, amount, price, image);
                coupons.add(coupon);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return coupons;
    }

    @Override
    public ArrayList<Coupon> getCompanyCouponsByCompanyIDCategory(int companyId, Coupon.Category category) {
        int categoryId = category.ordinal();
        ArrayList<Coupon> coupons = new ArrayList<>();
        try {
            String sql = "SELECT * FROM coupons WHERE COMPANY_ID="+companyId+" AND CATEGORY_ID="+categoryId;
            ResultSet resultset = dbm.runGetQuery(sql);
            while (resultset.next()) {
                int idDB = resultset.getInt("id");
                int companyID = resultset.getInt("company_id");
                String title = resultset.getString("title");
                String description = resultset.getString("description");
                Date startDate = resultset.getDate("start_date");
                Date endDate = resultset.getDate("end_date");
                int amount = resultset.getInt("amount");
                int price = resultset.getInt("price");
                String image = resultset.getString("image");
                Coupon coupon = new Coupon(idDB, companyID, category, title, description,startDate, endDate, amount, price, image);
                coupons.add(coupon);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return coupons;

    }

    @Override
    public ArrayList<Coupon> getCompanyCouponsByCompanyID(int companyId) {
        ArrayList<Coupon> coupons = new ArrayList<>();
        try {
            String sql = "SELECT * FROM coupons WHERE COMPANY_ID="+companyId;
            ResultSet resultset = dbm.runGetQuery(sql);
            while (resultset.next()) {
                int idDB = resultset.getInt("id");
                int companyID = resultset.getInt("company_id");
                String title = resultset.getString("title");
                String description = resultset.getString("description");
                Date startDate = resultset.getDate("start_date");
                Date endDate = resultset.getDate("end_date");
                int amount = resultset.getInt("amount");
                int price = resultset.getInt("price");
                String image = resultset.getString("image");
                int categoryID = resultset.getInt("category_id");
                Coupon.Category category = findCategoryById(categoryID);
                Coupon coupon = new Coupon(idDB, companyID, category, title, description,startDate, endDate, amount, price, image);
                coupons.add(coupon);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return coupons;
    }

    @Override
    public ArrayList<Integer> getCompanyCouponsIDs(int companyId) {
        ArrayList<Integer> IDs = new ArrayList<>();
        try {
            String sql = "SELECT ID FROM coupons where COMPANY_ID="+companyId;
            ResultSet resultset = dbm.runGetQuery(sql);
            while (resultset.next()) {
                int id = resultset.getInt("ID");
                IDs.add(id);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return IDs;
    }

    @Override
    public void deleteOldCoupons() throws SQLException {
        String sql="DELETE FROM coupons WHERE END_DATE < DATE '"+new java.sql.Date(new Date(System.currentTimeMillis()).getTime())+"'";
        int result = dbm.runUpdateQuery(sql);
        System.out.println("Old coupons removed");
    }


}
