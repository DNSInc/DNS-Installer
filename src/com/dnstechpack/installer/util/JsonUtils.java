package com.dnstechpack.installer.util;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.FileReader;
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
        JsonReader reader = new JsonReader(new FileReader(oldProfile.getCanonicalFile()));
        JsonWriter writer = new JsonWriter(new FileWriter(newProfile.getCanonicalFile()));
        
        writer.setIndent("    ");
        writer.beginObject();
        writer.name("profiles");
        writer.beginObject();
        writer.name("dns");
        writer.beginObject();
        writer.name("name").value("DNS Techpack");
        writer.endObject();
        writer.endObject();
        writer.endObject();
        writer.close();
        reader.close();
    }
}
