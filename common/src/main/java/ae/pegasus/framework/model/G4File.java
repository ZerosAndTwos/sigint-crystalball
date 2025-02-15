package ae.pegasus.framework.model;

import javax.ws.rs.core.MediaType;
import java.io.File;
import java.net.URI;

/**
 * G4File extend java.io.File with MediaType field (used in ingestion)
 */
public class G4File extends File {

    private MediaType mediaType;

    public G4File(String pathname) {
        super(pathname);
    }

    public G4File(String parent, String child) {
        super(parent, child);
    }

    public G4File(File parent, String child) {
        super(parent, child);
    }

    public G4File(URI uri) {
        super(uri);
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public void setMediaType(String type, String subType) {
        this.mediaType = new MediaType(type, subType);
    }

    public void setMediaTypeByFileExtension() {
        String[] filenameParts = this.getName().split("\\.");
        String extension = filenameParts[filenameParts.length - 1].toLowerCase();

        switch (extension) {
            case "wav":
                this.setMediaType(PegasusMediaType.AUDIO);
                break;
            case "xls":
                this.setMediaType(PegasusMediaType.MS_EXCEL_TYPE);
                break;
            case "md":
                this.setMediaType(PegasusMediaType.APPLICATION_JSON_TYPE);
                break;
            case "tif":
                this.setMediaType(PegasusMediaType.TIFF);
                break;
            case "xml":
                this.setMediaType(PegasusMediaType.ZDF);
                break;
            case "eml":
                this.setMediaType(PegasusMediaType.EML);
                break;
            case "protobuf":
                this.setMediaType(PegasusMediaType.PROTOBUF);
                break;
            default:
                this.setMediaType(PegasusMediaType.TEXT_CSV_TYPE);
                break;
        }
    }
}
