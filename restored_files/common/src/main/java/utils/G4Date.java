package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class G4Date extends Date {

    public G4Date(Date date) {
        super(date.getTime());
    }

    public G4Date() {
        super();
    }

    @Override
    public String toString() {
        return new SimpleDateFormat("EEE MMM dd HH:mm").format(this);
    }

}
