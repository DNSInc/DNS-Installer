package com.dnstechpack.installer.util;

import java.io.File;

public class InstallerUtils {

	public static File defaultDir = new File(System.getProperty("user.home"), "AppData/Roaming/.dns");
	
	static {
		
		if(!defaultDir.exists()) {
			
			defaultDir.mkdirs();
		}
	}
}