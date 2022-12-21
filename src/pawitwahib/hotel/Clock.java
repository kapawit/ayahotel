/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pawitwahib.hotel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.Timer;
/**
 *
 * @author kapaw
 */
public class Clock {
  public void jam(JLabel timeLabel) {
    Timer timer = new Timer(1000, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String time = dateFormat.format(date);
        timeLabel.setOpaque(false);
        timeLabel.setBackground(null);
        timeLabel.setText(time);
      }
    });
    timer.start();
  }
  public void kalender(JLabel kalender){
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String dateString = dateFormat.format(date);
    kalender.setText(dateString);
  }
}
