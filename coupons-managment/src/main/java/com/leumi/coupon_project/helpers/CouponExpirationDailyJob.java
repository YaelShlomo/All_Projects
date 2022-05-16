package com.leumi.coupon_project.helpers;

import com.leumi.coupon_project.DAO.CouponDAO;
import com.leumi.coupon_project.DAO.CouponDBDAO;

import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;


//TODO: run in main and stop before the program ends
public class CouponExpirationDailyJob implements Runnable{

    private static final long EXPIRATION_THREAD_PERIOD_IN_MILLIS = 24*60*60*1000; //day
    private CouponDAO couponDAO;
    private boolean quit;
    Timer timer = new Timer();

    public CouponExpirationDailyJob() throws SQLException, ClassNotFoundException {
        this.couponDAO = CouponDBDAO.getInstance();
        this.quit = false;
    }

    public void initThread() {
        int delay = 0; // delay for 0 sec.
        System.out.println("Remove Expired Sessions Thread Initialized!");
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //go to coupons table, delete expired coupons+ delete from history
                //for now the deleted coupons will automatically be deleted from
                //history because it is a cascade foreign key
                try {
                    couponDAO.deleteOldCoupons();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }, delay, EXPIRATION_THREAD_PERIOD_IN_MILLIS);
    }

    public void stop(){
        timer.cancel();
    }

    @Override
    public void run() {
        initThread();
    }
}
