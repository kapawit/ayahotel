/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pawitwahib.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author kapaw
 */

public class Config {

    private static boolean cekIsian(String ip, String port, String username, String pass){
        String error = null;
        if(ip.isEmpty()){
            error += "\n Ip Address Can't be empty";
        }
        if(port.isEmpty()){
            error += "\n Port Can't be empty";
        }
        if(username.isEmpty()){
            error += "\n username Can't be empty";
        }
        if(pass.isEmpty()){
            error += "\n Password Can't be empty";
        } 
        if(error == null){
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Error : "+error);
            return false;
        }
       
    }
    
    public static boolean cekConfig() {
        Path filePath = Paths.get("src/pawitwahib/config/config.properties");
        if (Files.exists(filePath)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Database configuration file does not exists.");
            return false;
        }
    }
    
    public static void testPing(String ip){
        if(ip.isEmpty()){
            JOptionPane.showMessageDialog(null, "The IP address can't be ampty.");
        } else {
            try {
                InetAddress address = InetAddress.getByName(ip);
                boolean reachable = address.isReachable(5000);
                if (reachable) {
                    JOptionPane.showMessageDialog(null, "The IP address is reachable.");
                } else {
                    JOptionPane.showMessageDialog(null, "The IP address is not reachable.");            }
            } catch (UnknownHostException e) {
                JOptionPane.showMessageDialog(null, "The IP address is invalid.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
     
    public static void testConnection(String ip, String port, String username, String pass){
    cekIsian(ip, port,username, pass);
        String url = "jdbc:mysql://"+ip+":"+port+"/dbhotelaya";
        try {
            DriverManager.getConnection(url, username, pass);
            JOptionPane.showMessageDialog(null, "Test Connection Success.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Test Connection Failed." + e.getMessage());
        }
    }
    
    public static boolean saveDatabaseConfig(String ip, String port, String username, String pass){
        if(cekIsian(ip, port,username, pass) == true){
            Properties prop = new Properties();
            String fileName = "src/pawitwahib/config/config.properties";
            try {
              prop.setProperty("dbserver", ip);
              prop.setProperty("dbport", port);
              prop.setProperty("dbuser", username);
              prop.setProperty("dbpassword", pass);
              prop.store(new FileOutputStream(fileName), null);
              return true;
            } catch (IOException ex) {
              JOptionPane.showMessageDialog(null, "Error " + ex);
              return false;
            }
        } 
        return false;
    }
    
  
    public static Properties getDbConfig() throws IOException {
        String fileName = "src/pawitwahib/config/config.properties";
        Properties prop = new Properties();
        FileInputStream input = new FileInputStream(fileName);
        prop.load(input);
        return prop;
    }
      
}