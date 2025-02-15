package ae.pegasus.framework.utils;



import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;


public class ExcelOpr {

    public String path;
    public FileInputStream excel = null;
    public FileOutputStream fileOut = null;
    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;
    private XSSFRow row = null;
    private XSSFCell cell = null;
    private int deafultsheet = 0;
    private FileOutputStream excelout = null;

    public ExcelOpr(String path) {
        this.path = path;

        try {
            
            excel = new FileInputStream(path);
            ZipSecureFile.setMinInflateRatio(0);
            workbook = new XSSFWorkbook(excel);
            sheet = workbook.getSheetAt(deafultsheet);
            excel.close();
        } catch (Exception e) {
            System.err.println("Error Message =  " + e.getMessage());
            e.printStackTrace();
        }
    }
    // returns the row count in a sheet

    public int getRowCount(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1)
            return 0;
        else {
            sheet = workbook.getSheetAt(index);
            int number = sheet.getLastRowNum() + 1;
            return number;

        }

    }

    public ArrayList<Integer> getallrowsbasedoncolvalue(String sheetName, String colName, String Serachstring) {

        ArrayList<Integer> AllRows = new ArrayList<Integer>();
        sheet = workbook.getSheet(sheetName);
        int colIndex = 0;
        for (int colNum = 0; colNum < sheet.getRow(0).getLastCellNum(); colNum++) {
            if (sheet.getRow(0).getCell(colNum).toString().equalsIgnoreCase(colName)) {
                colIndex = colNum;
                break;
            }
        }
        for (int RowNum = 0; RowNum < sheet.getLastRowNum() + 1; RowNum++) {
            if (sheet.getRow(RowNum).getCell(colIndex).toString().equalsIgnoreCase(Serachstring)) {
                AllRows.add(RowNum);
            }
        }
        return AllRows;
    }

    public ArrayList<Integer> getallrowsbasedoncolvalue(String colName, String Serachstring) {

        ArrayList<Integer> AllRows = new ArrayList<Integer>();
        // sheet = workbook.getSheetAt(1);
        int colIndex = 0;
        for (int colNum = 0; colNum < sheet.getRow(0).getLastCellNum(); colNum++) {
            if (sheet.getRow(0).getCell(colNum).toString().equalsIgnoreCase(colName)) {
                colIndex = colNum;
                break;
            }
        }
        for (int RowNum = 0; RowNum < sheet.getLastRowNum() + 1; RowNum++) {
            if (sheet.getRow(RowNum).getCell(colIndex).toString().equalsIgnoreCase(Serachstring)) {
                AllRows.add(RowNum);
            }
        }
        return AllRows;
    }

    public HashMap getExcelData(String sheetName) {
        HashMap<Integer, HashMap<String, ArrayList<String>>> exceldata = new HashMap<Integer, HashMap<String, ArrayList<String>>>();
        try {
            ArrayList<Integer> Rows = getallrowsbasedoncolvalue("sheet1", "Run Test", "Y");
            int ctry = 0;
            sheet = workbook.getSheet(sheetName);
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
            System.out.println(colCount);
            ArrayList<String> value = new ArrayList<String>();
            // HashMap<Integer, HashMap<Integer, ArrayList<String>>> hmap = new
            // HashMap<Integer, HashMap<Integer, ArrayList<String>>>();
            HashMap<String, ArrayList<String>> data = new HashMap<String, ArrayList<String>>();
            XSSFRow currentrow = null;
            for (Integer row : Rows) {
                value.clear();
                currentrow = sheet.getRow(row);
                for (int j = 1; j < colCount; j++) {
                    if (currentrow.getCell(j) != null)
                        value.add(currentrow.getCell(j).toString());
                    else
                        value.add("");
                }
                data.put("the", value);
                exceldata.put(row, data);
            }
            // System.out.println(twoDArrayList);
        } catch (Exception e) {
            System.err.println("Error Message =  " + e.getMessage());
            e.printStackTrace();

        }

        return exceldata;
    }

    public HashMap getExcelData() {
       HashMap<Integer, ArrayList<String>> exceldata = new HashMap<Integer, ArrayList<String>>();
       // HashMap<Integer, ArrayList<Object>> exceldata = new HashMap<Integer, ArrayList<Object>>();
        try {
            ArrayList<Integer> Rows = getallrowsbasedoncolvalue("Run Test", "Y");
            // sheet = workbook.getSheetAt(1);
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
            System.out.println(colCount);
            ArrayList<String> value = new ArrayList<String>();

            XSSFRow currentrow = null;
            for (Integer row : Rows) {
                value.clear();
                currentrow = sheet.getRow(row);
                for (int j = 1; j < colCount; j++) {
                    if (currentrow.getCell(j) != null)
                        value.add(new DataFormatter().formatCellValue(currentrow.getCell(j)));

                    else
                        value.add("");

                }
                ArrayList copy = new ArrayList(value);
                exceldata.put(row, copy);
                // System.out.println(value);
                //System.out.println(exceldata.get(row));
            }

        } catch (Exception e) {
            System.err.println("Error Message =  " + e.getMessage());
            e.printStackTrace();

        }
        return exceldata;
    }

    public HashMap<String, ArrayList<String>> Rowdata(String sheetName) {
       // HashMap<Integer, HashMap<String, ArrayList<String>>> exceldata = new HashMap<Integer, HashMap<String, ArrayList<String>>>();
        HashMap<String, ArrayList<String>> data = new HashMap<String, ArrayList<String>>();
        try {
            ArrayList<Integer> Rows = getallrowsbasedoncolvalue(sheetName, "Run Test", "Y");
            sheet = workbook.getSheet(sheetName);
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
            System.out.println(colCount);

            ArrayList<String> value = new ArrayList<String>();
          //  HashMap excelRowData = new HashMap();
            XSSFRow currentrow = null;
            for (int j = 1; j < colCount; j++) {
                value.clear();
                for (Integer row : Rows) {
                    currentrow = sheet.getRow(row);
                    if (currentrow.getCell(j) != null)
                        value.add(currentrow.getCell(j).toString());
                    else
                        value.add("");
                }
                // System.out.println("the");
                ArrayList copy = new ArrayList(value);
                data.put(sheet.getRow(0).getCell(j).toString(), copy);
                // System.out.println(data.keySet());

                // System.out.println(data.get(sheet.getRow(0).getCell(j).toString()));

                System.out.println(data.get(sheet.getRow(0).getCell(j)));
            }

        } catch (Exception e) {
            System.err.println("Error Message =  " + e.getMessage());
            e.printStackTrace();

        }

        return data;

    }
    public HashMap<String, ArrayList<String>>  Rowdata() {

        HashMap<String, ArrayList<String>> data = new HashMap<String, ArrayList<String>>();
        try {
            ArrayList<Integer> Rows = getallrowsbasedoncolvalue("Run Test", "Y");
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
            ArrayList<String> value = new ArrayList<String>();
            HashMap excelRowData = new HashMap();
            XSSFRow currentrow = null;
            for (int j = 1; j < colCount; j++) {
                value.clear();
                for (Integer row : Rows) {
                    currentrow = sheet.getRow(row);
                    if (currentrow.getCell(j) != null)
                       // value.add(currentrow.getCell(j).toString());
                    value.add(new DataFormatter().formatCellValue(currentrow.getCell(j)));


                    else
                        value.add("");
                }
                ArrayList copy = new ArrayList(value);
                data.put(sheet.getRow(0).getCell(j).toString(), copy);


            }

        } catch (Exception e) {
            System.err.println("Error Message =  " + e.getMessage());
            e.printStackTrace();

        }
        return data;
    }

    public void Rowdatahas(String sheetName) {
        HashMap<Integer, HashMap<String, ArrayList<String>>> exceldata = new HashMap<Integer, HashMap<String, ArrayList<String>>>();
        try {
            ArrayList<Integer> Rows = getallrowsbasedoncolvalue("sheet1", "Run Test", "Y");
            sheet = workbook.getSheet(sheetName);
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
            System.out.println(colCount);
            HashMap<String, ArrayList<String>> data = new HashMap<String, ArrayList<String>>();
            ArrayList<String> value = new ArrayList<String>();
            HashMap excelRowData = new HashMap();
            XSSFRow currentrow = null;
            for (int j = 1; j < colCount; j++) {
                value.clear();
                for (Integer row : Rows) {
                    currentrow = sheet.getRow(row);
                    if (currentrow.getCell(j) != null)
                        value.add(currentrow.getCell(j).toString());
                    else
                        value.add("");
                }
                // System.out.println("the");
                excelRowData.put(sheet.getRow(0).getCell(j), value);
                System.out.println(excelRowData.keySet());
                System.out.println(excelRowData.get(sheet.getRow(0).getCell(j)));

                // System.out.println(excelRowData.get(sheet.getRow(0).getCell(j)));
            }

        } catch (Exception e) {
            System.err.println("Error Message =  " + e.getMessage());
            e.printStackTrace();

        }
    }


    public int getcolumnumber(String sheetName, String Colunm, int rownumber) {
        sheet = workbook.getSheet(sheetName);
        XSSFRow currow = sheet.getRow(rownumber);
        for (int i = 0; i < currow.getPhysicalNumberOfCells(); i++) {
            if (currow.getCell(i).toString().equalsIgnoreCase(Colunm))
                return i;

        }
        return -1;
    }

    public int getcolumnumber(String sheetName, String Colunm) {
        sheet = workbook.getSheet(sheetName);
        XSSFRow currow = sheet.getRow(0);
        for (int i = 0; i < currow.getPhysicalNumberOfCells(); i++) {
            if (currow.getCell(i).toString().equalsIgnoreCase(Colunm))
                return i;

        }
        return -1;
    }

    public int getcolumnumber(String Colunm) {
        sheet = workbook.getSheetAt(deafultsheet);
        XSSFRow currow = sheet.getRow(0);
        for (int i = 0; i < currow.getPhysicalNumberOfCells(); i++) {
            if (currow.getCell(i).toString().equalsIgnoreCase(Colunm))
                return i;

        }
        return -1;
    }

    public ArrayList<String> getcolumaftergivencolumn(String Colunm, String sheetname) {
        sheet = workbook.getSheet(sheetname);
        ArrayList<String> col = new ArrayList<String>();
        XSSFRow currow = sheet.getRow(0);
        int reqcol = 0;

        for (int i = 0; i < currow.getPhysicalNumberOfCells(); i++) {
            if (currow.getCell(i).toString().equalsIgnoreCase(Colunm)) {
                reqcol = i;
                break;
            }
        }
        for (int i = reqcol + 1; i < sheet.getRow(0).getPhysicalNumberOfCells(); i++) {
            col.add(currow.getCell(i).toString());
        }
        return col;
    }
    public void setCellData(String sheetName, HashMap<Integer, ArrayList<String>> data) {
        XSSFRow currentrow = null;
        int row = 1;
        sheet = workbook.getSheet(sheetName);

       // CellStyle style = workbook.createCellStyle();
      //style.setFillBackgroundColor(IndexedColors.RED.getIndex());
        for (Integer key : data.keySet()) {
            currentrow = sheet.getRow(row);
            if (currentrow == null)
                currentrow = sheet.createRow(row);
            row++;
            for (int i = 0; i < data.get(key).size(); i++) {
                cell = currentrow.getCell(i);
                if (cell == null)
                    cell = currentrow.createCell(i);
                if (data.get(key).get(i).equals("FAIL")) {
                    CellStyle style = workbook.createCellStyle();
                    style.setFillForegroundColor(IndexedColors.RED.getIndex());
                    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    style.setBorderBottom(BorderStyle.THIN);
                    style.setBorderTop(BorderStyle.THIN);
                    style.setBorderRight(BorderStyle.THIN);
                    style.setBorderLeft(BorderStyle.THIN);
                    cell.setCellStyle(style);
                }
                if (data.get(key).get(i).equals("PASS")) {
                    CellStyle style = workbook.createCellStyle();
                    style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
                    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    style.setBorderBottom(BorderStyle.THIN);
                    style.setBorderTop(BorderStyle.THIN);
                    style.setBorderRight(BorderStyle.THIN);
                    style.setBorderLeft(BorderStyle.THIN);
                    cell.setCellStyle(style);
                }
                cell.setCellValue(data.get(key).get(i).toString());
               // cell.setCellStyle(style);

            }
        }
        try {
            // excel = new FileInputStream(path);
            // workbook = new XSSFWorkbook(excel);
            excelout = new FileOutputStream(path);
            workbook.write(excelout);
            excelout.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void setCellData1(String sheetName, HashMap<Integer, ArrayList<Object>> data) {
        XSSFRow currentrow = null;
        int row = 1;
        sheet = workbook.getSheet(sheetName);
        CellStyle cellStyle = workbook.createCellStyle();
        for (Integer key : data.keySet()) {
            currentrow = sheet.getRow(row);
            if (currentrow == null)
                currentrow = sheet.createRow(row);
            row++;
            for (int i = 0; i < data.get(key).size(); i++) {
                cell = currentrow.getCell(i);
                if (cell == null)
                    cell = currentrow.createCell(i);
                System.out.println(data.get(key).get(i).getClass());
                cell.setCellValue(data.get(key).get(i).toString());

            }
        }
        try {
            // excel = new FileInputStream(path);
            // workbook = new XSSFWorkbook(excel);
            excelout = new FileOutputStream(path);
            workbook.write(excelout);
            excelout.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public void clearSheetdata(String sheetName) {
        sheet = workbook.getSheet(sheetName);
        XSSFRow currentrow = null;
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            currentrow = sheet.createRow(i);
            sheet.removeRow(currentrow);
        }
        try {
            // excel = new FileInputStream(path);
            // workbook = new XSSFWorkbook(excel);
            excelout = new FileOutputStream(path);
            workbook.write(excelout);
            excelout.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public  void copyFileUsingApacheCommonsIO(String source, String path) throws IOException {
        String dest = path + "/output/";

        File sourceFile = new File(source);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formatDateTime = LocalDateTime.now().format(formatter);

        File targetFile = new File(dest +formatDateTime+".xlsx");
        FileUtils.copyFile(sourceFile, targetFile);
    }

}