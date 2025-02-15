package model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class FileAttachment {
    
    private String filename;
    private File file;

    public String getTitle() {
        return title;
    }

    private String title;


    public String getFilename() {
        return filename;
    }

    public File getFile() {
        return file;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public FileAttachment(String filename) {
            createAttachment(filename);
    }

    public FileAttachment(){}

    private void createAttachment(String name){
        File attachFile;
        this.title = name;
        try {
            attachFile = File.createTempFile(name, ".txt");
            FileOutputStream os = new FileOutputStream(attachFile);
            os.write("test file".getBytes());
            os.close();
            this.file = attachFile;
            this.filename = attachFile.getName();
        } catch (IOException e) {
            throw new AssertionError("Error occurred creating attachment file");
        }
    }
}
