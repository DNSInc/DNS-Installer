package com.dnstechpack.installer.util;

import com.dnstechpack.installer.enums.EnumErrorCodes;
import com.dnstechpack.installer.settings.Settings;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


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

    public static void shutdown(EnumErrorCodes code, Exception ex) {

        if(code != EnumErrorCodes.CLIENT_QUIT) {

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
                writer.write("Error Code - " + code.code + newLine);
                writer.write("Description - " + code.description + newLine);

                ex.printStackTrace(print);

                print.close();
                writer.close();
            } catch(IOException e) {

                e.printStackTrace();
            }
        }
        System.exit(code.code);
    }
}