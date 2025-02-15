package file_generator;

import ae.pegasus.framework.json.JsonConverter;
import model.G4File;
import model.Target;
import model.TargetGroup;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

class TargetFileService implements FileService<Target> {

    @Override
    public G4File write(List<Target> targets) {

        Map<Integer, Object[]> data = new HashMap<>();

        data.put(1, new Object[] {"ID", "Name", "Type", "Phones", "Keywords", "Target Groups"}); // columns name

        for (int i = 0; i < targets.size(); i++) {
            List<String> targetFields = new ArrayList<>();

            log.debug("Write target to file: " + JsonConverter.toJsonString(targets.get(i)));

            //ID	Name	Type	Phones	Keywords    Target Groups
            targetFields.add(targets.get(i).getId());
            targetFields.add(targets.get(i).getName());
            targetFields.add(""); // TargetType moved to targetGroups
            targetFields.add(targets.get(i).getPhones().toString().replace("[", "").replace("]", ""));
            targetFields.add(targets.get(i).getKeywords().toString().replace("[", "").replace("]", ""));
            // fill target groups
            List<TargetGroup> targetGroups = targets.get(i).getGroups();
            String groups = "";
            for (TargetGroup targetGroup : targetGroups) {
                if (groups.isEmpty()) {
                    groups = groups.concat(targetGroup.getName());
                } else {
                    groups = groups.concat(", " + targetGroup.getName());
                }
            }
            targetFields.add(groups);

            Object[] array = new Object[targetFields.size()];
            Object[] row = targetFields.toArray(array);

            Integer rowNum = i + 2;
            data.put(rowNum, row);
        }

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Target sheet");

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

        G4File file = null;
        try {
            String fileName = "Targets-" + new Date().getTime() + ".xls";
            file = new G4File(path + fileName);
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
            log.info("Excel file with targets written successfully: " + file.getAbsolutePath());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return file;
    }

}
