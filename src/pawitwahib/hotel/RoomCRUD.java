/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pawitwahib.hotel;

import config.Koneksi;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RoomCRUD extends JFrame {
  private Connection con = Koneksi.Koneksi();
  private Statement stat;
  private ResultSet rs;
  private String sql;
    
  // GUI components
  private JButton btnCreate, btnUpdate, btnDelete;
  private JTable table;
  private JScrollPane scrollPane;
  private DefaultTableModel model;

  public RoomCRUD() {
    super("Room CRUD");
    setLayout(new CardLayout());
    model = new DefaultTableModel();
    table = new JTable(model);
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    model.setColumnIdentifiers(new String[]{"ID", "id_tipe", "No Kamar", "Status"});
    scrollPane = new JScrollPane(table);
    btnCreate = new JButton("Create");
    btnCreate.addActionListener(new ActionListener() {
      
    public void actionPerformed(ActionEvent e) {
    //  createRoom();
      }
    });

    btnUpdate = new JButton("Update");
    btnUpdate.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    //updateRoom();
      }
    });

    btnDelete = new JButton("Delete");
    btnDelete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // deleteRoom();
      }
    });

    JPanel pnlButtons = new JPanel();
    pnlButtons.add(btnCreate);
    pnlButtons.add(btnUpdate);
    pnlButtons.add(btnDelete);

    add(pnlButtons);
    add(scrollPane);

    setSize(500, 300);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    readRooms();
  }

  
  public void readRooms() {
      
  try {
    stat = con.createStatement();
    sql = "SELECT id, id_tipe, no_kamar, status FROM KAMAR";
    rs = stat.executeQuery(sql);

    while (rs.next()) {
      int id = rs.getInt("id");
      int id_tipe = rs.getInt("id_tipe");
      String no_kamar = rs.getString("no_kamar");
      String status = rs.getString("status");
      model.addRow(new Object[]{id, id_tipe, no_kamar, status});
    }

    rs.close();
    stat.close();
    con.close();
  } catch (SQLException se) {
    se.printStackTrace();
  } catch (Exception e) {
    e.printStackTrace();
  } 
}
}