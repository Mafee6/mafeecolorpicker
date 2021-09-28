package mafeecolorchooser;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class colorchooser {
	static StringSelection ss = new StringSelection("");
	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		
		Color clr;
		
		try {
			Robot r = new Robot();
			
			window.setUndecorated(true);
			window.setMinimumSize(new Dimension(99, 99));
			window.setSize(100, 100);
			window.setAlwaysOnTop(true);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setShape(new Ellipse2D.Double(0, 0, 100, 100));
			window.setVisible(true);
			window.setTitle("Mafee Color Picker");
			window.setLocationRelativeTo(null);
			window.setIconImage(new ImageIcon("icon.png").getImage());
			
			window.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent ev) {
					if(ev.getKeyChar() == 'c' || ev.getKeyChar() == 'C') {						
						Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, ss);
					} else if(ev.getKeyCode() == 27 || ev.getKeyCode() == 69) {
						System.exit(1);
					}
				}
			});
						
			while(true) {
				clr = r.getPixelColor((int) MouseInfo.getPointerInfo().getLocation().getX(), (int) MouseInfo.getPointerInfo().getLocation().getY());
				ss = new StringSelection((clr != null) ? String.format("%s, %s, %s", clr.getRed(), clr.getGreen(), clr.getBlue()) : "0, 0, 0");
				window.getContentPane().setBackground(clr);
				window.setLocation((int) MouseInfo.getPointerInfo().getLocation().getX() + 3, (int) MouseInfo.getPointerInfo().getLocation().getY() + 4);
			}
			
		} catch (AWTException e) {
			e.printStackTrace();
		}	
		
	}
}
