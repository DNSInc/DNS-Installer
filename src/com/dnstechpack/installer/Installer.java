package com.dnstechpack.installer;

import com.dnstechpack.installer.gui.InstallerFrame;
import com.dnstechpack.installer.util.InstallerUtils;

import javax.swing.SwingUtilities;


public class Installer {

    public static void main(String... args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                try {
                    new InstallerFrame();
                } catch(Exception e) {

                    e.printStackTrace();
                    InstallerUtils.shutdown(e);
                }
            }
        });

        System.out.println("ModPack Is For Version " + InstallerUtils.settings.getMCVersion());
    }
}