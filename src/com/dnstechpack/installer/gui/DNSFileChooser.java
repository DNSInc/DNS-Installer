package com.dnstechpack.installer.gui;

import javax.swing.JFileChooser;

public class DNSFileChooser extends JFileChooser {

	public DNSFileChooser() {
		
		this.setFileSelectionMode(DIRECTORIES_ONLY);
	}
}
