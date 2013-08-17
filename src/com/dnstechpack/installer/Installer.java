package com.dnstechpack.installer;

import com.dnstechpack.installer.gui.InstallerFrame;
import com.dnstechpack.installer.util.InstallerUtils;

import javax.swing.*;


public class Installer {

    public static void main(String... args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                new InstallerFrame();
            }
        });

        System.out.println("ModPack Is For Version " + InstallerUtils.settings.getMCVersion());
    }
}