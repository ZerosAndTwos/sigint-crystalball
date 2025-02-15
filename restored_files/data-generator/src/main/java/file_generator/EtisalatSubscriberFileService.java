package file_generator;

import model.EtisalatSubscriberEntry;
import model.G4File;
import ae.pegasus.framework.utils.FileHelper;
import ae.pegasus.framework.utils.RandomGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

class EtisalatSubscriberFileService implements FileService<EtisalatSubscriberEntry> {

    @Override
    public G4File write(List<EtisalatSubscriberEntry> entries) {
        log.info("Create Etisalat Subscriber file..");

        //yyyyMMdd-filename-No.DX
        String fileName = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "-etisalat"
                + new Date().getTime() + "-01." + RandomGenerator.generateCountryCode();
        G4File file = new G4File(path + fileName);

        for (EtisalatSubscriberEntry entry : entries) {
            FileHelper.writeLineToFile(file, entryToString(entry));
        }

        return file;
    }

    private String entryToString(EtisalatSubscriberEntry entry) {
        String DELIMETER = "~";
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        dateTimeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        return entry.getAction() + DELIMETER +
                entry.getPhoneNumber() + DELIMETER +
                entry.getAccountSuffix() + DELIMETER +
                entry.getPartyId() + DELIMETER +
                entry.getName() + DELIMETER +
                entry.getAccountNameArabic() + DELIMETER +
                entry.getUserIdOrName() + DELIMETER +
                entry.getInstallationBuilding() + DELIMETER +
                entry.getInstallationFlatNumber() + DELIMETER +
                entry.getInstallationFloor() + DELIMETER +
                entry.getInstallationStreetName() + DELIMETER +
                entry.getInstallationPlotNumber() + DELIMETER +
                entry.getInstallationMapNumber() + DELIMETER +
                entry.getInstallationSector() + DELIMETER +
                entry.getInstallationTownCode() + DELIMETER +
                entry.getInstallationTownName() + DELIMETER +
                entry.getInstallationTownEmirate() + DELIMETER +
                entry.getFirstAddressLine() + DELIMETER +
                entry.getSecondAddressLine() + DELIMETER +
                entry.getPoBoxNumber() + DELIMETER +
                entry.getCustomerCategoryCode() + DELIMETER +
                entry.getCustomerCategoryCodeDesc() + DELIMETER +
                dateTimeFormat.format(entry.getDateOfInstallation()) + DELIMETER +
                entry.getCountryCodeOriginal() + DELIMETER +
                entry.getCountry() + DELIMETER +
                entry.getSubscriberAccountStatusCode() + DELIMETER +
                entry.getSubscriberAccountStatusDesc() + DELIMETER +
                entry.getProductGroupCode() + DELIMETER +
                entry.getProductGroupDesc() + DELIMETER +
                entry.getProductCode() + DELIMETER +
                entry.getProductDesc() + DELIMETER +
                entry.getImsi() + DELIMETER +
                entry.getIdentificationTypeCode() + DELIMETER +
                entry.getIdentificationTypeDesc() + DELIMETER +
                entry.getIdentificationInfo() + DELIMETER +
                entry.getProvisionedRegionCode() + DELIMETER +
                entry.getProvisionedRegionCodeDesc() + DELIMETER +
                entry.getCityId() + DELIMETER +
                entry.getCityName() + DELIMETER +
                ((entry.getUpdatedDate() != null) ? dateFormat.format(entry.getUpdatedDate()) : "") + DELIMETER +
                ((entry.getDateOfDeactivation() != null) ? dateTimeFormat.format(entry.getDateOfDeactivation()) : "");
    }
}
