package com.dnstechpack.installer.gui;

import com.dnstechpack.installer.util.InstallerUtils;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class InstallerPanel extends JPanel {

    public static JTextField installDir;

    public InstallerPanel() {
		
		this.setLayout(null);
		
		installDir = new JTextField();
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
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.getButton() == 1) {
					
					new FileChooser(installDir);
				}
			}
		});
		this.add(installBrowse);
	}
}
