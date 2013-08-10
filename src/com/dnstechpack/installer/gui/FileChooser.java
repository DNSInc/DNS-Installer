package com.dnstechpack.installer.gui;

import com.dnstechpack.installer.util.InstallerUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class FileChooser extends JFrame {

    protected static FileBrowser browser;

    public static FileChooser INSTANCE;

	public FileChooser(JTextField field, File defaultDir) {
		
		this.setTitle("Choose Directory");
		this.setSize(new Dimension(500, 400));
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setVisible(true);
		
		browser = new FileBrowser(defaultDir, field);
		browser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		this.getContentPane().add(browser);

        INSTANCE = this;
	}

    public class FileBrowser extends JFileChooser {

        private JTextField textBox;
        private File defaultDir;

        public FileBrowser(File defaultDir, JTextField textBox) {

            super(defaultDir);
            this.textBox = textBox;
            this.defaultDir = defaultDir;
            setupCustom();
        }

        public void setupCustom() {

            this.addActionListener(new ActionListener() {

                boolean allowed = true;

                @Override
                public void actionPerformed(ActionEvent e) {

                    if(e.getActionCommand().equals("ApproveSelection")) {

                        try {

                            if(defaultDir == InstallerUtils.mcDefault) {

                                File file = new File(FileChooser.browser.getSelectedFile(), "versions/" + InstallerUtils.getMCVersion() + "/" + InstallerUtils.getMCVersion() + ".jar");

                                if(!file.exists()) {

                                    allowed = false;

                                    final JFrame frame = new JFrame("Error");
                                    frame.setSize(new Dimension(290, 150));
                                    frame.setResizable(false);
                                    frame.setLocationRelativeTo(null);
                                    frame.setVisible(true);

                                    JButton option = new JButton("OK");
                                    option.addMouseListener(new MouseAdapter() {

                                        @Override
                                        public void mouseClicked(MouseEvent e) {

                                            if(e.getButton() == 1) {

                                                frame.setVisible(false);
                                            }
                                        }
                                    });

                                    JOptionPane error = new JOptionPane();
                                    error.setMessage("Make Sure You Have Run The Launcher Once");
                                    error.setOptions(new Object[] {option});
                                    frame.getContentPane().add(error);

                                    return;
                                }
                            }

                            if(allowed) {

                                FileChooser.browser.textBox.setText(FileChooser.browser.getSelectedFile().getCanonicalPath());
                                FileChooser.INSTANCE.setVisible(false);
                            }
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
