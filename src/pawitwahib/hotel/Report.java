/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pawitwahib.hotel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import pawitwahib.config.Koneksi;
import java.sql.Timestamp;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JRViewer;



/**
 *
 * @author kapaw
 */
public class Report {
    private Connection con = Koneksi.Koneksi();
    private Statement stat;
    private ResultSet res;
    private String sql;
    private DefaultTableModel tableModelPrint;

    
    public void reportInvoice(JTable tbReport){
        DefaultTableModel t = new DefaultTableModel();
        t.addColumn("Kode Inv");
        t.addColumn("Nama Tamu");
        t.addColumn("User");
        t.addColumn("Dp");
        t.addColumn("Status");
        tbReport.setModel(t);
        try{
            sql = "SELECT * FROM invoice";
            stat = con.createStatement();
            res = stat.executeQuery(sql);
            while(res.next()){
                t.addRow(new Object[] {
                    res.getString("kode_inv"),
                    res.getString("id_tamu"),
                    res.getString("id_user"),
                    res.getString("dp"),
                    res.getString("status")
                });
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    public void reportInvoiceByDate(JTable tbReport, Date awal, Date akhir){
        Timestamp tsawal = new Timestamp(awal.getTime());
        Timestamp tsakhir = new Timestamp(akhir.getTime());


        DefaultTableModel t = new DefaultTableModel();
        t.addColumn("Kode Inv");
        t.addColumn("Nama Tamu");
        t.addColumn("User");
        t.addColumn("Dp");
        t.addColumn("Status");
        tbReport.setModel(t);
        try{
            sql = "SELECT * FROM invoice WHERE timestamp BETWEEN '"+tsawal+"'  AND '"+tsakhir+"'";
            stat = con.createStatement();
            res = stat.executeQuery(sql);
            while(res.next()){
                t.addRow(new Object[] {
                    res.getString("kode_inv"),
                    res.getString("id_tamu"),
                    res.getString("id_user"),
                    res.getString("dp"),
                    res.getString("status")
                });
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    public void printReportInvoice(Date awal, Date akhir){
        Timestamp tsawal = new Timestamp(awal.getTime());
        Timestamp tsakhir = new Timestamp(akhir.getTime());
        String query = "SELECT * FROM invoice";
        if(awal != null && akhir != null){
            query = "SELECT * FROM invoice WHERE timestamp BETWEEN '"+tsawal+"'  AND '"+tsakhir+"'";
        }
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("query", query);
            JasperReport report = JasperCompileManager.compileReport("src/pawitwahib/resources/report/reportInvoice.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, param, con );
            JasperViewer.viewReport(print, false);
        } catch (JRException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void reportKamar(JTable table){
        int i = 1;
        DefaultTableModel t = new DefaultTableModel();
        t.addColumn("No");
        t.addColumn("No.Kamar");
        t.addColumn("Kategori");
        t.addColumn("Harga");
        t.addColumn("Status");
        table.setModel(t);
        try{
            sql = "select * from kamar inner join tipe_kamar on kamar.id_tipe = tipe_kamar.id;";
            stat = con.createStatement();
            res = stat.executeQuery(sql);
            while(res.next()){
                t.addRow(new Object[] {
                    i +1,
                    res.getString("no_kamar"),
                    res.getString("kategori"),
                    res.getString("harga"),
                    res.getString("status")
                });
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }        
        
    }
    
    public void printReportKamar(){
        String query = "select * from kamar inner join tipe_kamar on kamar.id_tipe = tipe_kamar.id;";
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("query", query);
            JasperReport report = JasperCompileManager.compileReport("src/pawitwahib/resources/report/reportKamar.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, param, con );
            JasperViewer.viewReport(print, false);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }
}

