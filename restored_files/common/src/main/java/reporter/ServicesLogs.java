package reporter;

import com.google.common.io.Files;
import org.apache.log4j.Logger;
import utils.FileHelper;

import java.io.*;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class ServicesLogs {

    static Logger log = Logger.getLogger(ServicesLogs.class);
    
    public File getOutputLog() throws IOException {
        log.info("Getting output log");
        File outputDir = Files.createTempDir();
        File file = File.createTempFile("log", ".txt", outputDir);
        InputStream is = new FileInputStream("log4j/log.out");
        FileOutputStream stream = new FileOutputStream(file);
        FileHelper.writeToFile(is, stream);
        return outputDir;

    }
}


class Zipper {

    ByteArrayOutputStream  zip(File directory) throws IOException {
        ServicesLogs.log.debug("Archiving files from directory:"+directory);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
         ZipOutputStream zipOut = new ZipOutputStream(output);
         zipOut.setLevel(Deflater.DEFAULT_COMPRESSION);
        for (File file: directory.listFiles()) {
            addToZip(directory, file, zipOut);

        }
         zipOut.close();
         return output;
    }

    private void addToZip(File directory, File file, ZipOutputStream stream) throws IOException {
        ServicesLogs.log.debug("Adding file to zip output stream");
        FileInputStream in = new FileInputStream(file);
        String zipFilePath = file.getCanonicalPath().substring(directory.getCanonicalPath().length() + 1,
                file.getCanonicalPath().length());
        ZipEntry zipEntry = new ZipEntry(zipFilePath);
        stream.putNextEntry(zipEntry);
        byte[] buffer = new byte[8 * 1024];
        int length;
        while ((length = in.read(buffer)) >= 0) {
            stream.write(buffer, 0, length);
        }
        stream.closeEntry();
        in.close();

    }

        }
