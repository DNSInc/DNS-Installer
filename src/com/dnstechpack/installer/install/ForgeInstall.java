package com.dnstechpack.installer.install;

import com.dnstechpack.installer.util.InstallerUtils;
import org.apache.commons.io.FileUtils;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;


/**
 * @Author ShadowChild.
 */
public class ForgeInstall {

    public static boolean installForge(File mcDir) {

        File oldJar = new File(mcDir + "/versions/" + InstallerUtils.settings.getMCVersion() + "/" + InstallerUtils.settings.getMCVersion() + ".jar");
        File newJar = new File(mcDir + "/versions/" + InstallerUtils.settings.getJarVersion() + "/" + InstallerUtils.settings.getJarVersion() + ".jar");
        File oldJson = new File(mcDir + "/versions/" + InstallerUtils.settings.getMCVersion() + "/" + InstallerUtils.settings.getMCVersion() + ".json");
        File newJson = new File(mcDir + "/versions/" + InstallerUtils.settings.getJarVersion() + "/" + InstallerUtils.settings.getJarVersion() + ".json");

        try {
            System.out.println(oldJar.getCanonicalPath());
        } catch(IOException e) {
            e.printStackTrace();
        }

        if(!oldJar.exists()) {

            JOptionPane.showMessageDialog(null, "Please Run Minecraft With Version " + InstallerUtils.settings.getMCVersion() + " Before Installing!");
            return false;
        }

        if(!oldJson.exists()) {

            JOptionPane.showMessageDialog(null, "Json File Missing For Version " + InstallerUtils.settings.getMCVersion() + " Please Re-Run Minecraft");
            return false;
        }

        System.out.println("Creating DNS Version");
        try {

            FileUtils.copyFile(oldJar, newJar, true);
            FileUtils.copyFile(oldJson, newJson, true);
        } catch(IOException e) {

            e.printStackTrace();
        }
//        oldJar.renameTo(newJar);

        return true;
    }
}
