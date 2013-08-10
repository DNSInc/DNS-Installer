package com.dnstechpack.installer.util;

import java.io.File;

public class InstallerUtils {

	public static File dnsDefault = new File(System.getProperty("user.home"), "AppData/Roaming/.dns");
    public static File mcDefault = new File(System.getProperty("user.home"), "AppData/Roaming/.minecraft");
	
	static {
		
		if(!dnsDefault.exists()) {
			
			dnsDefault.mkdirs();
		}

        if(!mcDefault.exists()) {

            mcDefault.mkdirs();
        }
	}
}