package com.dnstechpack.installer.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class InstallerUtils {

	public static File dnsDefault = new File(System.getProperty("user.home"), "AppData/Roaming/.dns");
    public static File mcDefault = new File(System.getProperty("user.home"), "AppData/Roaming/.minecraft");
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

        FileUtils.moveFile(src, dest);

//		if (src.isDirectory()) {
//
//			if (!dest.exists()) {
//				dest.mkdir();
//				System.out.println("Directory copied from " + src + "  to " + dest);
//			}
//
//			String files[] = src.list();
//
//			for (String file : files) {
//
//				File srcFile = new File(src, file);
//				File destFile = new File(dest, file);
//
//				moveFolder(srcFile, destFile);
//			}
//
//		} else {
//
//			InputStream in = new FileInputStream(src);
//			OutputStream out = new FileOutputStream(dest);
//
//			byte[] buffer = new byte[1024];
//
//			int length;
//
//			while ((length = in.read(buffer)) > 0) {
//				out.write(buffer, 0, length);
//			}
//
//			in.close();
//			out.close();
//			System.out.println("File copied from " + src + " to " + dest);
//		}
	}

    public static String getMCVersion() {

        return "1.6.2";
    }
}