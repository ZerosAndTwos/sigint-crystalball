package file_generator;

import model.G4File;
import model.Phonebook;
import ae.pegasus.framework.utils.FileHelper;

import java.util.Date;
import java.util.List;

class PhoneBookFileService implements FileService<Phonebook> {

    /**
     * Records look like this:
     * <p>
     * 967700000000," Arthur King "," postpaid 123 Main St Phoenix AZ ",\N,"usa","Y","mobile"
     * <p>
     * Records are terminated by a newline character.
     * Some records have a carriage return character inside.
     * Some records have a newline character inside, preceded by a backslash.
     * Some records have 2 unknown \N fields next to each other.
     * // The parts are: phone number, name, address, \N, possibly another \N, country, provider, location.
     */
    @Override
    public G4File write(List<Phonebook> phonebooks) {
        G4File file;
        try {
            String fileName = "Phonebooks" + new Date().getTime() + ".csv";
            file = new G4File(path + fileName);
        } catch (NullPointerException e) {
            log.error(e.getMessage());
            throw new Error("Unable to create Phonebook CVS file!");
        }

        for (Phonebook phonebook : phonebooks) {
            FileHelper.writeLineToFile(file, toCSVString(phonebook));
        }

        log.info("Phonebook written successfully to cvs file: " + file.getAbsolutePath());
        return file;
    }

    /**
     * Convert phonebook entity to string line for Phonebook CSV file
     *
     * @param phonebook phonebook entity
     * @return string line for Phonebook CSV file
     */
    private String toCSVString(Phonebook phonebook) {
        return String.format(
                "\"%s\",\"%s\",\"%s\",\\N,\\N,\"%s\",\"%s\",\"%s\"",
                phonebook.getPhoneNumber(), phonebook.getName(), phonebook.getAddress(),
                phonebook.getCountry(), phonebook.getProvider(), phonebook.getLocation()
        );
    }

}
