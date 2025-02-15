import ae.pegasus.framework.model.G4Entity;
import data_generator.DataGenerator;
import file_generator.FileGenerator;
import model.*;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class G4FileGenerator {

    private static Logger logger = Logger.getLogger(G4FileGenerator.class);
    private static Map<String, String> keys = new HashMap<>();
    private static Class<? extends G4Entity> entityClass;
    private static String help = "readme.txt";

    public static void main(String[] args) {
        configure(args);

        if (keys.containsKey("h") || keys.containsKey("-help")) {
            printFile(help);
            return;
        }

        generateFile();
    }

    private static void configure(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                if (i + 1 < args.length) {
                    if (args[i + 1].toLowerCase().startsWith("-")) {
                        keys.put(args[i].substring(1), null);
                    } else {
                        keys.put(args[i].substring(1), args[i + 1]);
                    }
                } else {
                    keys.put(args[i].substring(1), null);
                }
            }
        }

        String type = getValue("t");
        if (type != null) {
            switch (type) {
                case "target":
                    entityClass = Target.class;
                    break;
                case "s_sms":
                    entityClass = SSMS.class;
                    break;
                case "f_sms":
                    entityClass = FSMS.class;
                    break;
                case "phonebook":
                    entityClass = Phonebook.class;
                    break;
                case "du":
                    entityClass = DuSubscriberEntry.class;
                    break;
                case "etisalat":
                    entityClass = EtisalatSubscriberEntry.class;
                    break;
                default:
                    break;
            }
        }
    }

    private static void printFile(String fileName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream in = classLoader.getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;
        try {
            while (((line = reader.readLine()) != null)) {
                System.out.println(line);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private static String getValue(String key) {
        String value;
        try {
            value = keys.get(key).toLowerCase();
        } catch (NullPointerException e) {
            return null;
        }
        return value;
    }

    private static void generateFile() {
        if (entityClass == null) {
            logger.error("Source type not defined, file doesn't generated");
        } else {
            String records = getValue("r");
            if (records == null) {
                logger.error("Record number is required, see -r argument");
                printFile(help);
                return;
            }

            int size = Integer.valueOf(records);
            logger.info("Source type: " + entityClass);
            logger.info("Generating " + size + " entries");
            List list = new DataGenerator(entityClass).produceList(size);
            logger.info("Entries was generated");
            File file = new FileGenerator(entityClass).write(list);

            if (file.length() > 0) {
                logger.info("File: " + file.getName() + " was generated");
            }
        }
    }

}
