package utils;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class FileHelper {

    private final static Logger log = Logger.getLogger(FileHelper.class);

    public static void writeLineToFile(File file, String line) {
        Writer writer = null;
        try {
            writer = new FileWriter(file, true);
            writer.write(line);
            writer.write("\r\n");
            //writer.write(System.getProperty("line.separator"));
            writer.flush();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ex) {
                    log.error(ex.getMessage());
                }
            }
        }
    }

    public static String writeToFile(final String filename, String context) {
        File file = new File(System.getProperty("user.home") + "/" + filename);
        writeLineToFile(file, context);
        return filename;
    }

    public static void writeToFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[8 * 1024];
        int length;
        try {
            while ((length = in.read(buffer)) >= 0) {
                out.write(buffer, 0, length);
            }
        } catch (IOException e) {
            log.error("Exception occurred writing to output stream");
            throw e;
        }
        IOUtils.closeQuietly(in);
        IOUtils.closeQuietly(out);
    }

    public static String readTxtFile(String fileName) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL fileURL = classloader.getResource(fileName);
        if (fileURL == null) {
            throw new AssertionError("Got null URL for " + fileName);
        }
        File file = new File(fileURL.getFile());

        try (FileReader reader = new FileReader(file)) {
            char[] buffer = new char[(int) file.length()];
            reader.read(buffer);
            return new String(buffer);
        } catch (IOException e) {
            log.error(e.getMessage());
            return "";
        }
    }

    public static List<File> getFilesByWildcards(String directoryName, String[] extensions) {
        File directory = new File(directoryName);
        return (List<File>)
                FileUtils.listFiles(directory, new WildcardFileFilter(extensions), TrueFileFilter.INSTANCE);
    }


    /**
     * Code snippet from https://stackoverflow.com/a/14427280/4894316
     *
     * @param dir directory with tar.gz files inside
     * @throws IOException
     */
    public static void extractTarGzFiles(File dir) throws IOException {
        File listDir[] = dir.listFiles();
        if (listDir == null || listDir.length == 0) {
            return;
        }

        for (File i : listDir) {
                /*  Warning! this will try and extract all files in the directory
                if other files exist, a for loop needs to go here to check that
                the file (i) is an archive file before proceeding */
            if ((i.isDirectory()) || (!i.getName().endsWith(".tar.gz"))) {
                continue;
            }

            log.info("Extracting file: " + i.getName());

            String fileName = i.toString();
            String tarFileName = fileName + ".tar";
            FileInputStream inStream = new FileInputStream(fileName);
            GZIPInputStream gInStream = new GZIPInputStream(inStream);
            FileOutputStream outStream = new FileOutputStream(tarFileName);
            byte[] buf = new byte[1024];
            int len;
            while ((len = gInStream.read(buf)) > 0) {
                outStream.write(buf, 0, len);
            }
            gInStream.close();
            outStream.close();

            // There should now be tar files in the directory
            // extract specific files from tar
            TarArchiveInputStream myTarFile = new TarArchiveInputStream(new FileInputStream(tarFileName));
            TarArchiveEntry entry = null;
            int offset;
            FileOutputStream outputFile = null;

            // read every single entry in TAR file
            while ((entry = myTarFile.getNextTarEntry()) != null) {
                // the following two lines remove the .tar.gz extension for the folder name
                String folderName = i.getName().substring(0, i.getName().lastIndexOf('.'));
                folderName = folderName.substring(0, folderName.lastIndexOf('.'));

                File outputDir = new File(i.getParent() + "/" + folderName + "/" + entry.getName());
                if (!outputDir.getParentFile().exists()) {
                    outputDir.getParentFile().mkdirs();
                }
                // if the entry in the tar is a directory, it needs to be created, only files can be extracted
                if (entry.isDirectory()) {
                    outputDir.mkdirs();
                } else {
                    byte[] content = new byte[(int) entry.getSize()];
                    offset = 0;
                    myTarFile.read(content, offset, content.length - offset);
                    outputFile = new FileOutputStream(outputDir);
                    IOUtils.write(content, outputFile);
                    outputFile.close();
                }
            }
            //close and delete the tar files, leaving the original .tar.gz and the extracted folders
            myTarFile.close();
            File tarFile = new File(tarFileName);
            tarFile.delete();
        }
    }
}
