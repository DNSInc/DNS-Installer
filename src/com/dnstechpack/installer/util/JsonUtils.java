package com.dnstechpack.installer.util;

import argo.format.PrettyJsonFormatter;
import argo.jdom.*;
import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Files;

import java.io.*;
import java.util.HashMap;


/**
 * @Author ShadowChild.
 */
public class JsonUtils {

    public static void updateProfile(String mcDir, String installDir) throws IOException {

        System.out.println(mcDir + "\n" + installDir);

    	File oldProfile = new File(mcDir, "/launcher_profiles.json");
        
        if(!oldProfile.exists()) {

        	System.out.println("Please run minecraft once before trying to install the pack");
        }
        
        oldProfile.renameTo(new File(mcDir, "/launcher_profiles_backup.json"));
        System.out.println("Creating A Backup Of User Profiles");

        JdomParser profileParser = new JdomParser();
        JsonRootNode profileNode = null;

        try {

//            System.out.println(oldProfile.getCanonicalPath());
            Reader reader = new FileReader(oldProfile);

            profileNode = profileParser.parse(reader);
        } catch(Exception e) {

            e.printStackTrace();
        }

        JsonField[] fields = new JsonField[] {

            JsonNodeFactories.field("name", JsonNodeFactories.string(InstallerUtils.settings.getProfileName())),
            JsonNodeFactories.field("lastVersionId", JsonNodeFactories.string(InstallerUtils.settings.getJarVersion())),
            JsonNodeFactories.field("gameDir", JsonNodeFactories.string(installDir)),
            JsonNodeFactories.field("javaArgs", JsonNodeFactories.string("-XX:MaxPermSize=1024m"))
        };

        if(profileNode != null) {

            HashMap<JsonStringNode, JsonNode> profileCopy = Maps.newHashMap(profileNode.getNode("profiles").getFields());
            HashMap<JsonStringNode, JsonNode> rootCopy = Maps.newHashMap(profileNode.getFields());
            profileCopy.put(JsonNodeFactories.string("DNS Techpack"), JsonNodeFactories.object(fields));
            JsonRootNode profileJsonCopy = JsonNodeFactories.object(profileCopy);

            rootCopy.put(JsonNodeFactories.string("profiles"), profileJsonCopy);

            profileNode = JsonNodeFactories.object(rootCopy);
        }

        try {

            BufferedWriter newWriter = Files.newWriter(oldProfile, Charsets.UTF_8);
            PrettyJsonFormatter.fieldOrderPreservingPrettyJsonFormatter().format(profileNode, newWriter);
            newWriter.close();
        } catch(Exception e) {

            e.printStackTrace();
        }

//        JsonWriter writer = new JsonWriter(new FileWriter(newProfile.getCanonicalFile()));
//
//        writer.setIndent("    ");
//        writer.beginObject();
//        writer.name("profiles");
//        writer.beginObject();
//        writer.name("Dns Techpack");
//        writer.beginObject();
//        writer.name("name").value("DNS Techpack");
//        writer.name("gameDir").value(InstallerUtils.dnsDefault.toString());
//        writer.name("lastVersionId").value(InstallerUtils.settings.getMCVersion() + "-DNS" + InstallerUtils.settings.getDNSVersion());
//        writer.endObject();
//        writer.endObject();
//        writer.name("selectedProfile").value("Dns Techpack");
//        writer.endObject();
//        writer.close();
        System.out.print("Adding DNS Profile");
//        newProfile.renameTo(new File(InstallerUtils.mcDefault + "/launcher_profiles.json"));
    }
}
