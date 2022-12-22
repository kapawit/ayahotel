/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pawitwahib.hotel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import pawitwahib.config.Koneksi;

/**
 *
 * @author kapaw
 */
public class Transaksi {
    
    public void saveReservasi(Date awal, Date akhir, JComboBox cbtipekamar, JComboBox cbidtamu){
        Statement stat;
        ResultSet res;
        String sql;
        Connection con = Koneksi.Koneksi();
        int id_user = new Login().getId();
        ComboItem selectedItemTipe = (ComboItem) cbtipekamar.getSelectedItem();
        int idTipe = selectedItemTipe.getId();
        ComboItem selectedItemTamu = (ComboItem) cbidtamu.getSelectedItem();
        int idTamu = selectedItemTamu.getId();
        Date date = new Date();
        Timestamp tsawal = new Timestamp(awal.getTime());
        Timestamp tsakhir = new Timestamp(akhir.getTime());
        Timestamp timestamp = new Timestamp(date.getTime());   
        try {
            sql = "insert into reservasi (id_tamu, id_user, id_tipe, tgl_awal, tgl_akhir, timestamp) values ("
                    + "'" + idTamu + "',"
                    + "'" + id_user + "',"
                    + "'" + idTipe + "',"
                    + "'" + tsawal + "',"
                    + "'" + tsakhir + "',"
                    + "'" + timestamp+ "')";
            stat = con.createStatement();
            stat.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data Reservasi berhasil disimpan");
        } catch (SQLException ex) {
            Logger.getLogger(Kamar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
