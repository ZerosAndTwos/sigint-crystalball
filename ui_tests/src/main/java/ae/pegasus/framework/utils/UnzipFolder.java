package ae.pegasus.framework.utils;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipFolder {
    public static String folderPath;


    public static void startUnzip(String path , String reportNumber) {
        folderPath = path;
        createOutputDir(folderPath + "/Output");
        unzipAllFilesInFolder(folderPath);
    }

    public static void createOutputDir(String dirPath){

        File dir = new File(dirPath);
        if(!dir.exists()) dir.mkdirs();


    }

    public static void unzipAllFilesInFolder(String folderPath){

        File directory = new File(folderPath);
        File[] fileList = directory.listFiles();

                //getFileNameEndWith(folderPath, ".zip");
                unzipAllFiles(getFileNameEndWith(folderPath, ".zip"), folderPath + "/Output");

        }






    public static String getFileNameStartingWith(String folderPath , String startWithString) {

        File directory = new File(folderPath);
        File[] fileList = directory.listFiles();
        for (File file : fileList) {
            if (file.getName().toString().startsWith(startWithString))
            return file.getName().toString();

        }



return null;
    }

    public static void clearDownloadFolder(String Path)
    {
        File directory = new File(Path);
        File[] fileList = directory.listFiles();
        for (File file : fileList) {
            if(file.isDirectory()) {
                try {
                    FileUtils.deleteDirectory(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
            file.delete();

        }
    }


    public static String getFileNameEndWith(String folderPath , String endString) {

        File directory = new File(folderPath);
        File[] fileList = directory.listFiles();
        for (File file : fileList) {
            if (file.getName().toString().endsWith(endString))
            return file.getName().toString();
        }

        return null;
    }



    public static void unzipAllFiles(String file, String outputpath){
        byte[] buffer = new byte[1024];
        ZipInputStream zis =
                null;
        try {
            zis = new ZipInputStream(new FileInputStream(folderPath + "/" + file));

            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                String fileName = ze.getName();
                File newFile = new File(outputpath + File.separator + fileName);
                System.out.println("file unzip : " + newFile.getAbsoluteFile());
                //create all non exists folders
                //else you will hit FileNotFoundException for compressed folder
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                ze = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();
            System.out.println("Done");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    }


