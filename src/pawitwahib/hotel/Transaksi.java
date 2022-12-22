/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pawitwahib.hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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
    
    public void getTransaksiReservasi(JTable tabletransaksi){
        Connection con = Koneksi.Koneksi();
        Statement stat;
        ResultSet res;
        String sql;
        String status;
        int i =1;
        DefaultTableModel t = new DefaultTableModel();
        t.addColumn("No");
        t.addColumn("Nama");
        t.addColumn("Status Member");
        t.addColumn("Tipe Kamar");
        t.addColumn("Tgl Awal");
        t.addColumn("Tgl Akhir");
        t.addColumn("id");
        tabletransaksi.setModel(t);
        try{
            sql = "SELECT * FROM reservasi inner join tamu on reservasi.id_tamu = tamu.id inner join tipe_kamar on reservasi.id_tipe ";
            stat = con.createStatement();
            res = stat.executeQuery(sql);
            while(res.next()){
                int s = Integer.parseInt(res.getString("status_member"));
                if(s != 0){
                    status ="Non Member";
                } else {
                    status = "Member";
                }
                t.addRow(new Object[] {
                    i++,
                    res.getString("nama"),
                    status,
                    res.getString("kategori"),
                    res.getString("tgl_awal"),
                    res.getString("tgl_akhir"),
                    res.getString("id")
                });
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        tabletransaksi.getColumn("id").setMaxWidth(0);
    }
    
    public void saveCekin(Date awal, Date akhir, JComboBox cbtipekamar, JComboBox cbidtamu){
        Statement stat;
        ResultSet res;
        String sql;
        Connection con = Koneksi.Koneksi();
        int idkamar = 0;
        int id_user = new Login().getId();
        ComboItem selectedItemTipe = (ComboItem) cbtipekamar.getSelectedItem();
        int idTipe = selectedItemTipe.getId();
        ComboItem selectedItemTamu = (ComboItem) cbidtamu.getSelectedItem();
        int idTamu = selectedItemTamu.getId();
        Date date = new Date();
        Timestamp tsawal = new Timestamp(awal.getTime());
        Timestamp tsakhir = new Timestamp(akhir.getTime());
        Timestamp timestamp = new Timestamp(date.getTime()); 
        try{
            sql = "SELECT * FROM kamar where id_tipe = '"+idTipe+"'";
            stat = con.createStatement();
            res = stat.executeQuery(sql);
            if(res.next()) {
                idkamar = res.getInt("id");
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        try {
            sql = "insert into inap (id_tamu, id_kamar, id_user, tgl_awal, tgl_akhir, timestamp) values ("
                    + "'" + idTamu + "',"
                    + "'" + id_user + "',"
                    + "'" + idkamar + "',"
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
    
    public void getTransaksiCekin(JTable tablecekin){
        Connection con = Koneksi.Koneksi();
        Statement stat;
        ResultSet res;
        String sql;
        String status;
        int i =1;
        DefaultTableModel t = new DefaultTableModel();
        t.addColumn("No");
        t.addColumn("Nama");
        t.addColumn("no_kamar");
        t.addColumn("Tgl Awal");
        t.addColumn("Tgl Akhir");
        t.addColumn("Member");
        t.addColumn("id");
        tablecekin.setModel(t);
        try{
            sql = "SELECT * FROM inap RIGHT join tamu on inap.id_tamu = tamu.id join kamar on kamar.id=inap.id_kamar WHERE DATE(timestamp) = CURDATE();";
            stat = con.createStatement();
            res = stat.executeQuery(sql);
            while(res.next()){
                int s = Integer.parseInt(res.getString("status_member"));
                if(s != 0){
                    status ="Non Member";
                } else {
                    status = "Member";
                }
                t.addRow(new Object[] {
                    i++,
                    res.getString("nama"),
                    res.getString("no_kamar"),
                    res.getString("tgl_awal"),
                    res.getString("tgl_akhir"),
                    status,
                    res.getString("id")
                });
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        tablecekin.getColumn("id").setMaxWidth(0);
    }
    
    public void cekout(JTable tablereservasi, JButton btntransaksi){
        Connection con = Koneksi.Koneksi();
        Statement stat;
        ResultSet res;
        String sql;
        int i = tablereservasi.getSelectedRow();
        if(i==-1){
            JOptionPane.showMessageDialog(null, "Pilih salah satu baris");  
        } else {
            String id = (String)tablereservasi.getValueAt(i,6);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String tglawal = (String)tablereservasi.getValueAt(i,3);
            LocalDate date = LocalDate.parse(tglawal.substring(0, tglawal.length() - 2), formatter);
            int ok=JOptionPane.showConfirmDialog(null,"Chekout Tamu ini???", "Confirmation",JOptionPane.YES_NO_CANCEL_OPTION);
            if (ok==0){
                try{
                    sql = "UPDATE inap SET tgl_keluar = CURDATE(), tgl_masuk = '"+date+"' where id='"+id+"'";
                    PreparedStatement st = con.prepareStatement(sql);
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Chekout Sukses");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "chekout Gagal");
                }
            }
            btntransaksi.doClick();
        }
    }
}
