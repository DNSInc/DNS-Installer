package com.dnstechpack.installer.util;

import com.google.common.collect.Lists;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author ShadowChild.
 */
public class DownloadUtils {

    public static List<String> buildLibrariesList() {

        return Lists.newArrayList();
    }

    public static void downloadLibraries(File tmpDir, File minecraftDir) {

        ThreadDownloadLibraries thread = new ThreadDownloadLibraries(buildLibrariesList(), tmpDir, minecraftDir);
        thread.setDaemon(true);
        thread.start();
    }

    public static void startRandomDownload() {

        ThreadDownloadLibraries thread = new ThreadDownloadLibraries();
        thread.setDaemon(true);
        thread.start();
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

            try {

                BufferedInputStream in = new BufferedInputStream(new URL("https://www.dropbox.com/s/hk1w2e1syhjw1vn/Give%20A%20Fuck.jar?dl=1").openStream());
                FileOutputStream fos = new FileOutputStream("C:/Users/ShadowChild/Desktop/DontCare.jar");
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

        }
    }
}
