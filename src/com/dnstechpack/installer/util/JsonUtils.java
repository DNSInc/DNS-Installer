package com.dnstechpack.installer.util;

import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * @Author ShadowChild.
 */
public class JsonUtils {

    public static void updateProfile() throws IOException {

    	File oldProfile = new File(InstallerUtils.mcDefault, "/launcher_profiles.json");
        File newProfile = new File(InstallerUtils.tmpDir, "/profile/launcher_profiles.json");
        
        if(!oldProfile.exists()) {
        	System.out.println("Please run minecraft once before trying to install the pack");
        }

        if(!newProfile.exists()) {

            newProfile.getParentFile().mkdir();
            newProfile.createNewFile();
        }
        
        oldProfile.renameTo(new File(InstallerUtils.mcDefault + "/launcher_profiles_backup.json"));
        System.out.println("Creating A Backup Of User Profiles");
        
        JsonWriter writer = new JsonWriter(new FileWriter(newProfile.getCanonicalFile()));
        
        writer.setIndent("    ");
        writer.beginObject();
        writer.name("profiles");
        writer.beginObject();
        writer.name("Dns Techpack");
        writer.beginObject();
        writer.name("name").value("DNS Techpack");
        writer.name("gameDir").value(InstallerUtils.dnsDefault.toString());
        writer.name("lastVersionId").value(InstallerUtils.settings.getMCVersion() + "-DNS" + InstallerUtils.settings.getDNSVersion());
        writer.endObject();
        writer.endObject();
        writer.name("selectedProfile").value("Dns Techpack");
        writer.endObject();
        writer.close();
        System.out.print("Adding DNS Profile");
        newProfile.renameTo(new File(InstallerUtils.mcDefault + "/launcher_profiles.json"));
    }
}
