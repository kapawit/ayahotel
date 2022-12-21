/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pawitwahib.hotel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author kapaw
 */
public class Beranda {
   public void setBackgroundImage(JPanel panel) {
    ImageIcon icon = new ImageIcon("src/pawitwahib/resources/asset/ayahotel.jpg");
    JLabel label = new JLabel(icon);
    panel.add(label);
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
}

}
