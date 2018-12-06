package irs.gc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tuli
 */
import java.sql.*;
import javax.swing.*;

public class SqliteConnection {
    Connection conn = null;
    public static Connection dbconnector(){
        try{
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:E:\\project\\NetBeansProjects\\IRS.GC\\database\\IRS.employee.sqlite");
        //JOptionPane.showMessageDialog(null, "Connection success");
        return conn;
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        return null;
        }
    }
}
