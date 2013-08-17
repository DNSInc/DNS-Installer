package com.dnstechpack.installer.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;


public class InstallerUtils {

    public static File appData = new File(System.getenv("APPDATA"));
    public static File dnsDefault = new File(appData, "/.dns");
    public static File mcDefault = new File(appData, "/.minecraft");
    public static File mods = new File("/mods");

    static {

        if(!dnsDefault.exists()) {

            dnsDefault.mkdirs();
        }

        if(!mcDefault.exists()) {

            mcDefault.mkdirs();
        }
    }

    public static void moveFolder(File src, File dest) throws IOException {

        // TODO: Use Our Own Again! commons version renames the folder, not moves it

        FileUtils.moveDirectory(src, dest);
    }

    public static String getMCVersion() {

        return "1.6.2";
    }
}