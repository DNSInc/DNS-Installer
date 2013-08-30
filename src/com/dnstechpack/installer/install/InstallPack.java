package com.dnstechpack.installer.install;

import com.dnstechpack.installer.enums.EnumErrorCodes;
import com.dnstechpack.installer.util.InstallerUtils;
import com.dnstechpack.installer.util.JsonUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.FileUtils;


/**
 * @Author ShadowChild.
 */
public class InstallPack extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {

        try {

            ZipFile zipFile = new ZipFile(new File("./pack/pack.zip").getCanonicalFile());
            Enumeration<?> enu = zipFile.entries();

            while(enu.hasMoreElements()) {

                ZipEntry zipEntry = (ZipEntry) enu.nextElement();

                String name = zipEntry.getName();
                long size = zipEntry.getSize();
                long compressedSize = zipEntry.getCompressedSize();
                System.out.printf("name: %-20s | size: %6d | compressed size: %6d\n", name, size, compressedSize);

                File file = new File(InstallerUtils.tmpDir.getAbsolutePath() + "/" + name);
                if(name.endsWith("/")) {

                    file.mkdirs();
                    continue;
                }

                File parent = file.getParentFile();
                if(parent != null) {

                    parent.mkdirs();
                }

                InputStream is = zipFile.getInputStream(zipEntry);
                FileOutputStream fos = new FileOutputStream(file);
                byte[] bytes = new byte[1024];
                int length;

                while((length = is.read(bytes)) >= 0) {

                    fos.write(bytes, 0, length);
                }
                is.close();
                fos.close();
            }
            zipFile.close();

            JsonUtils.updateProfile();
        } catch(Exception e1) {

            e1.printStackTrace();
            InstallerUtils.shutdown(EnumErrorCodes.INSTALL_ERROR, e1);
        }
        
    	File toDNS = new File(InstallerUtils.tmpDir, "/toDNS");
        File toMC = new File(InstallerUtils.tmpDir, "/toMC");
        File mc = new File(InstallerUtils.mcDefault + "/");
        File dns = new File(InstallerUtils.dnsDefault + "/");
        
        try {
			FileUtils.copyDirectory(toDNS, dns);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        try {
			FileUtils.copyDirectory(toMC, mc);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
}
