package com.dnstechpack.installer.gui;

import java.awt.Dimension;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.dnstechpack.installer.util.InstallerUtils;

public class FileChooser extends JFrame {
	
	public FileChooser() {
		
		this.setTitle("Choose Directory");
		this.setSize(new Dimension(500, 400));
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setVisible(true);
		
		JFileChooser browser = new JFileChooser(InstallerUtils.defaultDir);
		browser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		this.getContentPane().add(browser);
	}
}
