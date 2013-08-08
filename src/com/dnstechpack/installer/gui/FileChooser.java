package com.dnstechpack.installer.gui;

import com.dnstechpack.installer.util.InstallerUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class FileChooser extends JFrame {

    protected static FileBrowser browser;

    public static FileChooser INSTANCE;

	public FileChooser(JTextField field) {
		
		this.setTitle("Choose Directory");
		this.setSize(new Dimension(500, 400));
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setVisible(true);
		
		browser = new FileBrowser(InstallerUtils.defaultDir, field);
		browser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		this.getContentPane().add(browser);

        INSTANCE = this;
	}

    public class FileBrowser extends JFileChooser {

        private JTextField textBox;

        public FileBrowser(File defaultDir, JTextField textBox) {

            super(defaultDir);
            this.textBox = textBox;
            setupCustom();
        }

        public void setupCustom() {

            this.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    if(e.getActionCommand().equals("ApproveSelection")) {

                        try {

                            FileChooser.browser.textBox.setText(FileChooser.browser.getSelectedFile().getCanonicalPath());
                            FileChooser.INSTANCE.setVisible(false);
                        } catch (IOException e1) {

                            e1.printStackTrace();
                        }
                    } else if(e.getActionCommand().equals("CancelSelection")) {

                        FileChooser.INSTANCE.setVisible(false);
                    }
                }
            });
        }
    }
}
