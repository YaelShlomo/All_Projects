package com.leumi.coupon_project.controllers;

import com.leumi.coupon_project.helpers.Credentials;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import java.sql.SQLException;

public abstract class ClientController {
    public abstract ResponseEntity<?> login(@RequestBody Credentials cred) throws SQLException;
}