package com.dnstechpack.installer.util;

import com.dnstechpack.installer.gui.InstallerPanel;
import com.google.common.collect.Lists;

import javax.swing.JOptionPane;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;


/**
 * @Author ShadowChild.
 */
public class DownloadUtils {

    public static ArrayList<String> buildLibrariesList() {

        return Lists.newArrayList();
    }

    public static void downloadLibraries(File tmpDir, File minecraftDir) {

        ThreadDownloadLibraries thread = new ThreadDownloadLibraries(buildLibrariesList(), tmpDir, minecraftDir);
        thread.setDaemon(true);
        thread.start();
    }

    public static void startForgeLibsDownload() {

        JOptionPane.showMessageDialog(null, "Please Wait While We Download The Forge Libs\nThis May Take Some Time");

        try {

            BufferedInputStream in = new BufferedInputStream(new URL("http://www.dnstechpack.com/user/madcock83/libraries.zip").openStream());
            FileOutputStream fos = new FileOutputStream(new File(InstallerUtils.tmpDir, "libraries.zip"));
            BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
            byte data[] = new byte[1024];
            int count;
            while((count = in.read(data, 0, 1024)) != -1) {

                bout.write(data, 0, count);
            }
            bout.close();
            in.close();
        } catch(Exception e) {

            e.getStackTrace();
        }

//        ThreadDownloadLibraries thread = new ThreadDownloadLibraries();
//        thread.setDaemon(true);
//        thread.start();
    }

    public static class ThreadDownloadLibraries extends Thread {

        private ArrayList<String> libraries;
        private File tmpDir;
        private File minecraftDir;

        public ThreadDownloadLibraries(ArrayList<String> libraries, File tmpDir, File minecraftDir) {

            this.libraries = libraries;
            this.tmpDir = tmpDir;
            this.minecraftDir = minecraftDir;
        }

        public ThreadDownloadLibraries() {

        }

        @Override
        public void run() {

            JOptionPane.showMessageDialog(null, "Please Wait While We Download The Forge Libs");

            try {

                BufferedInputStream in = new BufferedInputStream(new URL("http://www.dnstechpack.com/user/madcock83/libraries.zip").openStream());
                FileOutputStream fos = new FileOutputStream(new File(InstallerUtils.tmpDir, "libraries.zip"));
                BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
                byte data[] = new byte[1024];
                int count;
                while((count = in.read(data, 0, 1024)) != -1) {

                    bout.write(data, 0, count);
                }
                bout.close();
                in.close();
            } catch(Exception e) {

                e.getStackTrace();
            }
            // temp code
            InstallerPanel.progress.setValue(70);
        }
    }
}
