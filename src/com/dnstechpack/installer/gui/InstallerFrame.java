package com.dnstechpack.installer.gui;

import com.dnstechpack.installer.util.ErrorCodes;
import com.dnstechpack.installer.util.InstallerUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class InstallerFrame extends JFrame {

    public InstallerFrame() {

        super("DNS Techpack Installer");
        this.setSize(new Dimension(365, 450));
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {

                InstallerUtils.shutdown(ErrorCodes.CLIENT_QUIT, null);
            }
        });
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        InstallerPanel mainPanel = new InstallerPanel();
        this.getContentPane().add(mainPanel);
    }
}
