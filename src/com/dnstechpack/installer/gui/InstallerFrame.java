package com.dnstechpack.installer.gui;

import javax.swing.*;
import java.awt.*;


public class InstallerFrame extends JFrame {

    public InstallerFrame() {

        super("DNS Techpack Installer");
        this.setSize(new Dimension(365, 450));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        InstallerPanel mainPanel = new InstallerPanel();
        this.getContentPane().add(mainPanel);
    }
}
