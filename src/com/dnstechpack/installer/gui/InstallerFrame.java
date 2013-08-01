package com.dnstechpack.installer.gui;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class InstallerFrame extends JFrame {

	public InstallerFrame() {
		
		super("DNS Techpack Installer");
		this.setSize(new Dimension(400, 600));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		InstallerPanel mainPanel = new InstallerPanel();
		this.getContentPane().add(mainPanel);
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {

				if(e.getKeyChar() == 'q') {
					
					new FileChooser();
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
