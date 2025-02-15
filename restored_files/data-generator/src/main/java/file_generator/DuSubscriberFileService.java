package file_generator;

import model.DuSubscriberEntry;
import model.G4File;
import ae.pegasus.framework.utils.FileHelper;

import java.util.Date;
import java.util.List;

class DuSubscriberFileService implements FileService<DuSubscriberEntry> {

    @Override
    public G4File write(List<DuSubscriberEntry> entries) {
        log.info("Create Du Subscriber file..");

        String fileName = "duSubscriber" + new Date().getTime() + ".csv";
        G4File file = new G4File(path + fileName);

        for (DuSubscriberEntry entry : entries) {
            FileHelper.writeLineToFile(file, entryToString(entry));
        }
        return file;
    }

    private String entryToString(DuSubscriberEntry entry) {
        String DELIMETER = "~";
        return entry.getPhoneNumber() + DELIMETER +
                entry.getTitle() + DELIMETER +
                entry.getFirstName() + DELIMETER +
                entry.getMiddleName() + DELIMETER +
                entry.getLastName() + DELIMETER +
                entry.getPoBox() + DELIMETER +
                entry.getCity() + DELIMETER +
                entry.getNationality() + DELIMETER +
                entry.getVisaType() + DELIMETER +
                entry.getVisaNumber() + DELIMETER +
                entry.getIdType() + DELIMETER +
                entry.getIdNumber() + DELIMETER +
                entry.getStatus() + DELIMETER +
                entry.getCustomerType() + DELIMETER +
                entry.getServiceType() + DELIMETER +
                entry.getCustomerCode();
    }


}
