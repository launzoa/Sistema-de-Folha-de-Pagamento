/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sfp.core;

/**
 *
 * @author igor.nogueira_unesp
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoBD {
    private static final String URL = "jdbc:mysql://localhost:3306/SFP";
    private static final String USER = "root";
    private static final String PASS = "admin";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
