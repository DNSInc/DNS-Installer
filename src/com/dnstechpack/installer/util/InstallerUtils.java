package com.dnstechpack.installer.util;

import com.dnstechpack.installer.settings.Settings;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class InstallerUtils {

    public static File appData = new File(System.getenv("APPDATA"));
    public static File dnsDefault = new File(appData, "/.dns");
    public static File mcDefault = new File(appData, "/.minecraft");
    public static File tmpDir = new File("./tmp.dns");
    public static Settings settings = new Settings();
    public static String newLine = System.lineSeparator();

    static {

        if(!dnsDefault.exists()) {

            dnsDefault.mkdirs();
        }

        if(!mcDefault.exists()) {

            mcDefault.mkdirs();
        }

        if(!tmpDir.exists()) {

            tmpDir.mkdirs();
        }
    }

    public static void moveFolder(File src, File dest) throws IOException {

        // TODO: Use Our Own Again! commons version renames the folder, not moves it

        FileUtils.moveDirectory(src, dest);
    }

    public static void shutdown(Exception ex) {

        if(ex != null) {

            try {

                DateFormat format = new SimpleDateFormat("yyyy-mm-dd_kk-mm-ss");
                Date date = new Date();
                String theDate = format.format(date);

                File writeTo = new File("./crash-logs/" + theDate + ".log");
                writeTo.getParentFile().mkdir();
                writeTo.createNewFile();

                FileWriter writer = new FileWriter(writeTo, true);
                PrintWriter print = new PrintWriter(writer);
                writer.write("DNS Installer Crash Report" + newLine);
                writer.write("Please Send To A Rep At http://esper.net/ in channel #dns" + newLine);

                ex.printStackTrace(print);

                print.close();
                writer.close();
            } catch(IOException e) {

                e.printStackTrace();
            }
        }
        System.exit(-1);
    }

    public static void unZip(File zip, File destDir) {

        try {

            ZipFile zipFile = new ZipFile(zip);
            Enumeration<?> enu = zipFile.entries();

            while(enu.hasMoreElements()) {

                ZipEntry zipEntry = (ZipEntry) enu.nextElement();

                String name = zipEntry.getName();
                long size = zipEntry.getSize();
                long compressedSize = zipEntry.getCompressedSize();
                System.out.printf("name: %-20s | size: %6d | compressed size: %6d\n", name, size, compressedSize);

                File file = null;

                if(destDir == null) {

                    file = new File(InstallerUtils.tmpDir.getAbsolutePath(), name);
                } else {

                    file = new File(destDir, name);
                }
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

        } catch(Exception e1) {

            e1.printStackTrace();
            InstallerUtils.shutdown(e1);
        }
    }

    public static void unZip(File zip) {

        unZip(zip, null);
    }
}