package com.dnstechpack.installer.util;

import argo.format.PrettyJsonFormatter;
import argo.jdom.*;
import com.dnstechpack.installer.gui.InstallerPanel;
import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.HashMap;
import javax.swing.JOptionPane;


/**
 * @Author ShadowChild.
 */
public class JsonUtils {

    public static void updateProfile(String mcDir, String installDir) throws IOException {

        System.out.println(mcDir + "\n" + installDir);

        File oldProfile = new File(mcDir, "/launcher_profiles.json");

        if(!oldProfile.exists()) {

            JOptionPane.showMessageDialog("Please run minecraft once before trying to install the pack");
        }

        FileUtils.copyFile(oldProfile, new File(mcDir, "/launcher_Profiles_backup.json"), true);
        System.out.println("Creating A Backup Of User Profiles");

        JdomParser profileParser = new JdomParser();
        JsonRootNode profileNode = null;

        try {
            
            Reader reader = new FileReader(oldProfile);

            profileNode = profileParser.parse(reader);
        } catch(Exception e) {

            e.printStackTrace();
        }

        JsonField[] fields = new JsonField[] {

                JsonNodeFactories.field("name", JsonNodeFactories.string(InstallerUtils.settings.getProfileName())),
                JsonNodeFactories.field("lastVersionId", JsonNodeFactories.string(InstallerUtils.settings.getJarVersion())),
                JsonNodeFactories.field("gameDir", JsonNodeFactories.string(installDir)),
                JsonNodeFactories.field("javaArgs", JsonNodeFactories.string("-XX:MaxPermSize=1024m -Xmx1G"))
        };

        if(profileNode != null) {

            HashMap<JsonStringNode, JsonNode> profileCopy = Maps.newHashMap(profileNode.getNode("profiles").getFields());
            HashMap<JsonStringNode, JsonNode> rootCopy = Maps.newHashMap(profileNode.getFields());
            profileCopy.put(JsonNodeFactories.string(InstallerUtils.settings.getProfileName()), JsonNodeFactories.object(fields));
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
            InstallerUtils.shutdown(e);
        }

        System.out.print("Adding DNS Profile");
        InstallerPanel.progress.setValue(90);
    }
}
