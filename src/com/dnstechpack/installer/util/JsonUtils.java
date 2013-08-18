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

        File file = new File(InstallerUtils.tmpDir, "/profile/profile.json");

        if(!file.exists()) {

            file.getParentFile().mkdir();
            file.createNewFile();
        }
        JsonWriter writer = new JsonWriter(new FileWriter(file.getCanonicalFile()));

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
    }
}
