/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pawitwahib.config;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kapaw
 */

public class Koneksi {
    Connection con;   
    public static Connection Koneksi() {
        Properties prop;
        try {
            prop = Config.getDbConfig();  
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://"+prop.getProperty("dbserver")+":"+prop.getProperty("dbport")+"/dbhotelaya", prop.getProperty("dbuser"), prop.getProperty("dbpassword"));
            return con;
        } catch (ClassNotFoundException | SQLException | IOException e) {
            Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
}