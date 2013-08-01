package com.dnstechpack.installer.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.dnstechpack.installer.util.InstallerUtils;

public class InstallerPanel extends JPanel {

	public InstallerPanel() {
		
		this.setLayout(null);
		
		JTextField installDir = new JTextField();
		try {
			
			installDir.setText(InstallerUtils.defaultDir.getCanonicalPath());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		installDir.setEditable(true);
		installDir.setBounds(20, 30, 200, 20);
		this.add(installDir);
		
		JButton installBrowse = new JButton();
		installBrowse.setText("Browse");
		installBrowse.setBounds(230, 30, 100, 20);
		installBrowse.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.getButton() == 1) {
					
					new FileChooser();
				}
			}
		});
		this.add(installBrowse);
	}
}
