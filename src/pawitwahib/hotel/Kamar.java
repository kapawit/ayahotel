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

public class Kamar extends javax.swing.JFrame {
    private Connection con = Koneksi.Koneksi();
    private DefaultTableModel dtm;
    private JTable table;

    /**
     * Creates new form kamar
     */
    
    public Kamar() {
        initComponents();
        getTipeKamar(tipekamar);
        setLocationRelativeTo(null);
    }
    
    public void getKamar(JTable tablekamar){
        Statement stat;
        ResultSet res;
        String sql;
        String status;
        int i =1;
        DefaultTableModel t = new DefaultTableModel();
        t.addColumn("No");
        t.addColumn("No Kamar");
        t.addColumn("Tipe");
        t.addColumn("Harga");
        t.addColumn("Status");
        t.addColumn("id");
        tablekamar.setModel(t);
        try{
            sql = "SELECT * FROM kamar inner join tipe_kamar on kamar.id_tipe = tipe_kamar.id";
            stat = con.createStatement();
            res = stat.executeQuery(sql);
            while(res.next()){
                int s = Integer.parseInt(res.getString("status"));
                if(s != 0){
                    status ="Available";
                } else {
                    status = "Occupied";
                }
                t.addRow(new Object[] {
                    i++,
                    res.getString("no_kamar"),
                    res.getString("kategori"),
                    res.getString("harga"),
                    status,
                    res.getString("id")
                });
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        tablekamar.getColumn("id").setMaxWidth(0);
    }
    
    public void getTipeKamar(JComboBox cb){
        Statement stat;
        ResultSet res;
        String sql;
        try{
            sql = "SELECT * FROM tipe_kamar";
            stat = con.createStatement();
            res = stat.executeQuery(sql);
            while(res.next()){
                cb.addItem(res.getString("kategori"));
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        cb.setSelectedIndex(-1);
    }
    
     public String getKamarKosong(String status, int tipe){
        Statement stat;
        ResultSet res;
        String sql;
        String result = null;
        try{
            sql = "SELECT * FROM kamar where status = '"+status+"' and id_tipe ='"+Integer.sum(tipe,1)+"' limit 1";
            stat = con.createStatement();
            res = stat.executeQuery(sql);
            if(res.next()) {
                result = res.getString("no_kamar");
            } else {
                result = null;
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        if(result != null){
            return result;
        } else {
            return null;
        }
    }
    
    private void savekamar(){
        Statement stat;
        ResultSet res;
        String sql;
        try {
            sql = "insert into kamar (id_tipe, no_kamar, status)  values ("
                    + "'" + Integer.sum(tipekamar.getSelectedIndex(), 1)+ "',"
                    + "'" + nokamar.getText()+ "',"
                    + "'" + statuskamar.getText()+ "')";
            stat = con.createStatement();
            stat.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data kamar berhasil disimpan");
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(Kamar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void hapusKamar(JTable tablekamar, JButton btnkamar){
        Statement stat;
        ResultSet res;
        String sql;
        int i = tablekamar.getSelectedRow();
        if(i==-1){
            JOptionPane.showMessageDialog(null, "Pilih salah satu baris");  
        } else {
            String id = (String)tablekamar.getValueAt(i,5);
            int ok=JOptionPane.showConfirmDialog(null,"Apakah Yakin Mendelete record ini???", "Confirmation",JOptionPane.YES_NO_CANCEL_OPTION);
            if (ok==0){
                try{
                    sql="delete from kamar where id='"+id+"'";
                    PreparedStatement st = con.prepareStatement(sql);
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Delete Data Sukses");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Delete Data Gagal");
                }
            }
            btnkamar.doClick();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        btnsavekamar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tipekamar = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        nokamar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        statuskamar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnsavekamar.setText("SIMPAN");
        btnsavekamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsavekamarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("TAMBAH KAMAR");

        jLabel2.setText("Tipe Kamar");

        jLabel3.setText("No Kamar");

        jLabel4.setText("Status");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tipekamar, 0, 151, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nokamar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(statuskamar)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tipekamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nokamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(statuskamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnsavekamar))
                    .addComponent(jSeparator1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsavekamar)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsavekamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsavekamarActionPerformed
       savekamar();
    }//GEN-LAST:event_btnsavekamarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnsavekamar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField nokamar;
    private javax.swing.JTextField statuskamar;
    private javax.swing.JComboBox<String> tipekamar;
    // End of variables declaration//GEN-END:variables
}
