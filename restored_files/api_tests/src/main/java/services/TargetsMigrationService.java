package services;

import app_context.properties.G4Properties;
import http.G4HttpClient;
import http.G4Response;
import http.OperationResult;
import http.requests.TargetMigrationRequest;
import model.*;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static http.G4HttpClient.Protocol.HTTP;
import static json.JsonConverter.toJsonString;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static utils.DateHelper.dateToFormat;
import static utils.RandomGenerator.*;


public class TargetsMigrationService {

    private static final Path IMPORT_DIR = Paths.get(System.getProperty("user.dir"), "importTargetsData");
    private static final String FILENAME = "import-data.xlsx";
    private static XSSFWorkbook workbook = new XSSFWorkbook();
    private static G4HttpClient g4HttpClient = new G4HttpClient(G4Properties.getRunProperties().getApplicationURL(), HTTP);
    private static final TargetMigrationRequest request = new TargetMigrationRequest();
    private static Logger log = Logger.getLogger(TargetsMigrationService.class);
    private static final String DATE_FORMAT = "YYYY-MM-dd";

    public static void writeTargets(List<Profile> targets) {

        Map<Integer, Object[]> data = new HashMap<>();
        data.put(1, new Object[]{"ID", "name", "description", "category", "criminalRecord", "classification", "active", "activeUntil", "targetGroupsID", "g4Id"}); // columns name

        log.info("Targets_List data...");

        Integer rowNum = 2;
        Iterator<Profile> iterator = targets.iterator();
        while (iterator.hasNext()) {
            Profile target = iterator.next();
            try {
                List<String> fields = new ArrayList<>(); // new target row

                fields.add(target.getId()); // ID
                fields.add(target.getName()); // name
                fields.add(target.getProperties().getDescription()); // description
                fields.add(target.getCategory().equals("Person of Interest") ?
                        ProfileCategory.POI.getDisplayName() : ProfileCategory.Dangerous.getDisplayName()); // category
                fields.add(target.getCriminalRecord().name()); // criminalRecord
                fields.add(target.getClassification()); // classification
                fields.add(target.getActive().name()); // active
                fields.add(dateToFormat(target.getActiveUntil(), DATE_FORMAT));// activeUntil

                // fill targetGroupsID
                List<TargetGroup> targetGroups = target.getGroups();
                String groupIDs = "";
                for (TargetGroup group : targetGroups) {
                    groupIDs = groupIDs.isEmpty() ? groupIDs.concat(group.getId()) : groupIDs.concat("," + group.getId());
                }
                fields.add(groupIDs);

                // g4Id
                if (nextInt(0, 10) == 0) { // generate 10% of record with g4Id
                    fields.add(generateID());
                } else {
                    fields.add("");
                }

                Object[] array = new Object[fields.size()];
                Object[] row = fields.toArray(array);

                data.put(rowNum, row);
                rowNum++;
            } catch (NullPointerException e) {
                log.error("Skipping target with invalid fields data:" + toJsonString(target));
                iterator.remove();
            }
        }
        writeSheet(data, "Targets_List");
    }

    public static void writeGroups(List<TargetGroup> groups) {

        log.info("Target_Groups_List data...");

        Map<Integer, Object[]> data = new HashMap<>();
        data.put(1, new Object[]{"ID", "targetGroup", "parentID", "team"}); // columns name

        for (int i = 0; i < groups.size(); i++) {
            List<String> fields = new ArrayList<>(); // new group row
            fields.add(groups.get(i).getId()); // ID
            fields.add(groups.get(i).getName()); // name

            // parentID
            if (i % 2 == 1) {
                fields.add(groups.get(generateRandomInteger(0, i - 1)).getId());
            } else {
                fields.add("");
            }

            // team
            String fieldTeam = "";
            for (String team : groups.get(i).getAssignedTeams()) {
                fieldTeam = fieldTeam.isEmpty() ? fieldTeam.concat(team) : fieldTeam.concat(", " + team);
            }
            fields.add(fieldTeam); // team

            Object[] array = new Object[fields.size()];
            Object[] row = fields.toArray(array);

            Integer rowNum = i + 2;
            data.put(rowNum, row);
        }
        writeSheet(data, "Target_Groups_List");
    }

    public static void writeIdentifiers(List<Profile> targets) {

        log.info("Target_Identifiers_List data...");

        Map<Integer, Object[]> data = new HashMap<>();
        data.put(1, new Object[]{"ID", "target", "type", "name", "sources"}); // columns name

        Integer identifiersTotal = 0;
        for (Profile target : targets) {
            Integer identifiersCount = target.getIdentifiers().size();
            for (int j = 0; j < identifiersCount; j++) {
                try {
                    List<String> fields = new ArrayList<>();// new identifiers row

                    fields.add(target.getId()); // ID
                    fields.add(target.getName()); // target
                    fields.add(target.getIdentifiers().get(j).getType().name()); // type

                    Assert.assertFalse(target.getIdentifiers().get(j).getValue().isEmpty());
                    fields.add(target.getIdentifiers().get(j).getValue()); // name

                    fields.add(target.getIdentifiers().get(j).getSources().get(0).name()); // source

                    Object[] array = new Object[fields.size()];
                    Object[] row = fields.toArray(array);

                    Integer rowNum = identifiersTotal + 2;
                    data.put(rowNum, row);
                    identifiersTotal++;
                } catch (Exception e) {
                    log.warn("Skipping target_Identifier with invalid value:" + toJsonString(target.getIdentifiers().get(j)));
                }
            }
        }
        writeSheet(data, "Target_Identifiers_List");
    }

    public static void writeManualAttributes(List<Profile> targets) {
        log.info("Target_Manual_Attributes_List data...");

        List<String> types = Arrays.asList("NAME", "GENDER", "NATIONALITY", "DATE OF BIRTH", "DOCUMENT");
        List<String> genders = Arrays.asList("M", "F");

        Map<Integer, Object[]> data = new HashMap<>();
        data.put(1, new Object[]{"ID", "target", "type", "value", "document country"}); // columns name

        Integer attributtesTotal = 0;
        for (Profile target : targets) {
            Integer numAttributes = generateRandomInteger(0, 3);
            for (int i = 0; i < numAttributes; i++) {
                List<String> fields = new ArrayList<>();// new manual attributes row
                fields.add(target.getId()); // ID
                fields.add(target.getName()); // target

                String currType = getRandomItemFromList(types);
                fields.add(currType); // type

                switch (currType) { // value
                    case "NAME":
                        fields.add(randomAlphabetic(10));
                        break;
                    case "GENDER":
                        fields.add(getRandomItemFromList(genders));
                        break;
                    case "NATIONALITY":
                        fields.add(CountyCode.random().name());
                        break;
                    case "DATE OF BIRTH":
                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.DAY_OF_MONTH, generateRandomInteger(-28000, -3000));
                        fields.add(dateToFormat(calendar.getTime(), DATE_FORMAT));
                        break;
                    case "DOCUMENT":
                        fields.add(randomNumeric(10));
                        break;
                    default:
                        log.error("Wrong attribute type:" + currType);
                        break;
                }

                if (currType.equals("DOCUMENT")) { // document country
                    fields.add(CountyCode.random().name());
                } else {
                    fields.add("");
                }

                Object[] array = new Object[fields.size()];
                Object[] row = fields.toArray(array);
                Integer rowNum = attributtesTotal + 2;
                data.put(rowNum, row);
                attributtesTotal++;
            }
        }
        writeSheet(data, "Target_Manual_Attributes_List");
    }

    private static void writeSheet(Map<Integer, Object[]> data, String sheetName) {
        XSSFSheet sheet = workbook.createSheet(sheetName);

        Set<Integer> keyset = data.keySet();
        int rownum = 0;
        for (Integer key : keyset) {
            Row row = sheet.createRow(rownum++);

            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof Date)
                    cell.setCellValue((Date) obj);
                else if (obj instanceof Boolean)
                    cell.setCellValue((Boolean) obj);
                else if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Double)
                    cell.setCellValue((Double) obj);
            }
        }
    }

    public static G4File writeFile() {
        G4File file = null;
        try {
            if (Files.exists(IMPORT_DIR)) {
                FileUtils.deleteQuietly(new File(IMPORT_DIR.toString()));
            }
            File dir = new G4File(IMPORT_DIR.normalize().toString());
            if (!dir.exists()) {
                Assert.assertTrue("Unable create directory: " + IMPORT_DIR.normalize().toString(), dir.mkdirs());
            }

            String absolutePath = Paths.get(IMPORT_DIR.toString(), FILENAME).toString();
            file = new G4File(absolutePath);
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
            log.info("Excel file with Targets_List sheet written successfully: " + absolutePath);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return file;
    }

    public static OperationResult uploadTargetsFile(G4File file) {
        log.info("Import Targets file to env...");
        G4Response response = g4HttpClient.sendRequest(request.uploadTargets(file));

        OperationResult<Profile> operationResult = new OperationResult<>(response);
        if (operationResult.isSuccess()) {
            log.info(operationResult.getCode() + ", " + operationResult.getMessage());
        } else {
            log.error(operationResult.getCode() + ", " + operationResult.getMessage());
        }
        return operationResult;
    }
}
