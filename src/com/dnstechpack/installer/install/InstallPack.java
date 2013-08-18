package com.dnstechpack.installer.install;

import com.dnstechpack.installer.enums.EnumErrorCodes;
import com.dnstechpack.installer.util.InstallerUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;


/**
 * @Author ShadowChild.
 */
public class InstallPack extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {

        try {

            InstallerUtils.moveFolder(new File("C:/Users/ShadowChild/Desktop/Test"), new File("C:Users/ShadowChild/Desktop/Test2"));
        } catch(IOException e1) {

            e1.printStackTrace();
            InstallerUtils.shutdown(EnumErrorCodes.INSTALL_ERROR, e1);
        }
    }
}
