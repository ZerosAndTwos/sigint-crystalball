package file_generator;

import model.G4File;
import model.PegasusMediaType;
import model.SSMS;
import ae.pegasus.framework.utils.FileHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

class SSMSFileService implements FileService<SSMS> {

    private String entityToCSVString(SSMS ssms) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return String.format("%s,\"%s\",%d,%d,%d," +
                        "%d,%d,%d,%d,\"%s\",\"%s\"," +
                        "\"%s\",\"%s\",\"%s\",%d,%d," +
                        "%s,\"%s\",%s,\"%s\"," +
                        "%s,\"%s\",%s,\"%s\"," +
                        "\"%s\"," +
                        "\"%s\",%d,\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"," +
                        "\"%s\",%d,\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"," +
                        "%s,%d,%s,\"%s\",\"%s\",\"%s\",\"%s\"",
                ssms.getCdr_id(), df.format(ssms.getEvent_time()), ssms.getUnit(), ssms.getMatrix_ts(), ssms.getNi(),
                ssms.getOpc(), ssms.getDpc(), ssms.getHit_count(), ssms.getMaskmarked(), ssms.getSrcip(), ssms.getDscip(),
                df.format(ssms.getSms_time()), ssms.getSmstimezone(), ssms.getTxt(), ssms.getHaveparts(), ssms.getTotalparts(),
                ssms.getCalled_imsi(), ssms.getCalled_imsi_cc(), ssms.getCalled_imsi_ac(), ssms.getCalled_imsi_hit(),
                ssms.getCaller_imsi(), ssms.getCaller_imsi_cc(), ssms.getCaller_imsi_ac(), ssms.getCaller_imsi_hit(),
                ssms.getPattern(),
                ssms.getCalled(), ssms.getCalled_non(), ssms.getToNumber(),
                ssms.getCalled_rule(), ssms.getCalled_hit(), ssms.getCalled_cc(), ssms.getCalled_ac(),
                ssms.getCaller(), ssms.getCaller_non(), ssms.getFromNumber(),
                ssms.getCaller_rule(), ssms.getCaller_hit(), ssms.getCaller_cc(), ssms.getCaller_ac(),
                ssms.getSmsc(), ssms.getSmsc_non(), ssms.getSmsc_mod(),
                ssms.getSmsc_rule(), ssms.getSmsc_hit(), ssms.getSmsc_cc(), ssms.getSmsc_ac()
        );
    }

    @Override
    public G4File write(List<SSMS> ssmsList) {
        String fileName = "SSMS" + new Date().getTime() + ".csv";
        G4File file = new G4File(path + fileName);
        file.setMediaType(PegasusMediaType.TEXT_CSV_TYPE);

        for (SSMS ssms : ssmsList) {
            FileHelper.writeLineToFile(file, entityToCSVString(ssms));
        }

        log.info("SSMS list written successfully to cvs file: " + file.getAbsolutePath());
        return file;
    }

}
