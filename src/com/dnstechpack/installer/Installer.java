package com.dnstechpack.installer;

import javax.swing.SwingUtilities;

import com.dnstechpack.installer.gui.InstallerFrame;

public class Installer {

	public static InstallerFrame frame;
	
	public static void main(String... args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {

				frame = new InstallerFrame();
			}
		});
	}
}
