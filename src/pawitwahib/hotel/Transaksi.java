/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pawitwahib.hotel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author kapaw
 */
public class Transaksi {
    
    private void saveReservasi(int tipe, String no, Date awal, Date Akhir){
        Statement stat;
        ResultSet res;
        String sql;
//        try {
//            sql = "insert into reservasi (id_tipe, no_kamar, status)  values ("
//                    + "'" + Integer.sum(tipekamar.getSelectedIndex(), 1)+ "',"
//                    + "'" + nokamar.getText()+ "',"
//                    + "'" + statuskamar.getText()+ "')";
//            stat = con.createStatement();
//            stat.executeUpdate(sql);
//            JOptionPane.showMessageDialog(null, "Data kamar berhasil disimpan");
//            this.dispose();
//        } catch (SQLException ex) {
//            Logger.getLogger(Kamar.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
