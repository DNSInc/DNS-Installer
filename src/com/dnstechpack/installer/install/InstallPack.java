package com.dnstechpack.installer.install;

import com.dnstechpack.installer.gui.InstallerPanel;
import com.dnstechpack.installer.util.InstallerUtils;
import com.dnstechpack.installer.util.JsonUtils;
import org.apache.commons.io.FileUtils;

import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


/**
 * @Author ShadowChild.
 */
public class InstallPack extends MouseAdapter {

    public InstallPack() {

        super();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        InstallerPanel.install.setEnabled(false);
        new InstallThread(InstallerPanel.mcDir.getText(), InstallerPanel.installDir.getText());
    }

    public class InstallThread extends Thread {

        private String mcDir;
        private String installDir;

        public InstallThread(String mcDir, String installDir) {

            super();

            this.mcDir = mcDir;
            this.installDir = installDir;

            this.setDaemon(true);
            this.setName("InstallTread");
            this.start();
        }

        @Override
        public void run() {

//            DownloadUtils.startRandomDownload();

            File mcJar = new File(mcDir + "/versions/" + InstallerUtils.settings.getMCVersion() + "/" + InstallerUtils.settings.getMCVersion() + ".jar");
            try {
                System.out.println(mcJar.getCanonicalPath());
            } catch(IOException e) {
                e.printStackTrace();
            }
            if(!mcJar.exists()) {

                JOptionPane.showMessageDialog(null, "Please Run Minecraft With Version " + InstallerUtils.settings.getMCVersion() + " Before Installing!");
                return;
            }

            InstallerPanel.progress.setValue(10);

            unZip();

            File toDNS = new File(InstallerUtils.tmpDir, "/toDNS");
            File toMC = new File(InstallerUtils.tmpDir, "/toMC");
            File mc = new File(mcDir + "/");
            File dns = new File(installDir + "/");

            if(ForgeInstall.installForge(mc)) {

                try {

                    FileUtils.copyDirectory(toDNS, dns);
                    FileUtils.copyDirectory(toMC, mc);
                    JsonUtils.updateProfile(mcDir, installDir);
                } catch(IOException e) {

                    e.printStackTrace();
                    InstallerUtils.shutdown(e);
                }

                try {

                    FileUtils.cleanDirectory(InstallerUtils.tmpDir);
                } catch(IOException e) {

                    e.printStackTrace();
                    InstallerUtils.shutdown(e);
                }

                InstallerPanel.progress.setValue(100);
                JOptionPane.showMessageDialog(null, "Finished Installing");
                InstallerPanel.install.setEnabled(true);
            }
        }

        private void unZip() {

            try {

                ZipFile zipFile = new ZipFile(new File("./pack/pack.zip").getCanonicalFile());
                Enumeration<?> enu = zipFile.entries();

                while(enu.hasMoreElements()) {

                    ZipEntry zipEntry = (ZipEntry) enu.nextElement();

                    String name = zipEntry.getName();
                    long size = zipEntry.getSize();
                    long compressedSize = zipEntry.getCompressedSize();
                    System.out.printf("name: %-20s | size: %6d | compressed size: %6d\n", name, size, compressedSize);

                    File file = new File(InstallerUtils.tmpDir.getAbsolutePath() + "/" + name);
                    if(name.endsWith("/")) {

                        file.mkdirs();
                        continue;
                    }

                    File parent = file.getParentFile();
                    if(parent != null) {

                        parent.mkdirs();
                    }

                    InputStream is = zipFile.getInputStream(zipEntry);
                    FileOutputStream fos = new FileOutputStream(file);
                    byte[] bytes = new byte[1024];
                    int length;

                    while((length = is.read(bytes)) >= 0) {

                        fos.write(bytes, 0, length);
                    }
                    is.close();
                    fos.close();
                }
                zipFile.close();

                InstallerPanel.progress.setValue(50);
            } catch(Exception e1) {

                e1.printStackTrace();
                InstallerUtils.shutdown(e1);
            }
        }
    }
}
