package com.dnstechpack.installer.gui;

import com.dnstechpack.installer.util.InstallerUtils;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class InstallerPanel extends JPanel {

    public static JTextField installDir;
    public static JTextField mcDir;

    public InstallerPanel() {
		
		this.setLayout(null);

        JLabel insLabel, mcLabel;

		installDir = new JTextField();
        mcDir = new JTextField();
        insLabel = new JLabel("ModPack Install Directory");
        mcLabel = new JLabel("Base Minecraft Directory");

        insLabel.setBounds(20, 10, 200, 20);
        this.add(insLabel);

        mcLabel.setBounds(20, 80, 200, 20);
        this.add(mcLabel);

		try {
			
			installDir.setText(InstallerUtils.dnsDefault.getCanonicalPath());
            mcDir.setText(InstallerUtils.mcDefault.getCanonicalPath());
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
					
					new FileChooser(installDir, InstallerUtils.dnsDefault);
				}
			}
		});
		this.add(installBrowse);

        JButton mcBrowse = new JButton();
        mcBrowse.setText("Browse");
        mcBrowse.setBounds(230, 100, 100, 20);
        mcBrowse.addMouseListener(new MouseListener() {

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

                if (e.getButton() == 1) {

                    new FileChooser(mcDir, InstallerUtils.mcDefault);
                }
            }
        });
        this.add(mcBrowse);

        mcDir.setEditable(true);
        mcDir.setBounds(20, 100, 200, 20);
        this.add(mcDir);
	}
}
