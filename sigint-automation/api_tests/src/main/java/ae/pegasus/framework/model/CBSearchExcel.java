package ae.pegasus.framework.model;

import ae.pegasus.framework.utils.ExcelOpr;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class CBSearchExcel {

    public  HashMap<Integer, ArrayList<String>> exceldata = new HashMap<Integer, ArrayList<String>>();
    public HashMap<String, ArrayList<String>> rowdata =  new HashMap<String, ArrayList<String>>();
    public String workingDir = "";
   // public ArrayList<String> output = new ArrayList<String>();
    public  HashMap<Integer, ArrayList<String>> outputdata = new HashMap<Integer, ArrayList<String>>();
    private ExcelOpr op;
    public ArrayList<Integer> rows =new ArrayList<Integer>();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    String date = LocalDateTime.now().format(formatter).toString();

    public CBSearchExcel(String excelpath ) {


        workingDir = System.getProperty("user.dir") + excelpath;
        System.out.printf(workingDir);
        op = new ExcelOpr(workingDir);
        exceldata = op.getExcelData();
        rowdata = op.Rowdata();
        rows = op.getallrowsbasedoncolvalue("Run Test", "Y");

    }
    public void getRequiredResult(int key , int reposonsecode , double totalcount, String error, int row, String eventime)
    {

        ArrayList copy = new ArrayList(exceldata.get(key));
        copy.add(String.valueOf(reposonsecode));
        copy.add(String.valueOf(totalcount));
        copy.add(String.valueOf(error));
        copy .add(date);
        copy.add(eventime.toString());
        String a  = "==";
        if (reposonsecode == Integer.parseInt(copy.get(5).toString()) &&
            compareByCriteria(copy.get(7).toString(),Integer.parseInt(copy.get(6).toString()),totalcount))
            copy.add(String.valueOf("PASS"));

       else
             copy.add(String.valueOf("FAIL"));

        outputdata.put(key, copy);
       // System.out.println(" the op is " + outputdata.get(key));

    }


    public void getRequiredResult(int key , int reposonsecode , double totalcount, String error, int row)
    {

        ArrayList copy = new ArrayList(exceldata.get(key));
        copy.add(String.valueOf(reposonsecode));
        copy.add(String.valueOf(totalcount));
        copy.add(String.valueOf(error));
        copy .add(date);
        String a  = "==";
        if (reposonsecode == Integer.parseInt(copy.get(5).toString()) &&
                compareByCriteria(copy.get(7).toString(),Integer.parseInt(copy.get(6).toString()),totalcount))
            copy.add(String.valueOf("PASS"));

        else
            copy.add(String.valueOf("FAIL"));

        outputdata.put(key, copy);
        // System.out.println(" the op is " + outputdata.get(key));

    }

    public void getRequiredResult( int key ,int reposonsecode , double totalcount, String error,int row, Boolean entity)
    {

        ArrayList copy = new ArrayList(exceldata.get(key));
        copy.add(String.valueOf(reposonsecode));
        copy.add(String.valueOf(totalcount));
        copy.add(String.valueOf(error));
        copy .add(LocalDateTime.now().format(formatter).toString());
        copy.add(String.valueOf("FAIL"));
        outputdata.put(key, copy);
        // System.out.println(" the op is " + outputdata.get(key));

    }
    public void writeOutputExcel(  )
    {
        op.clearSheetdata("Output");
        op.setCellData("Output", outputdata);

       // op.setCellData1("Output", outputdata);

    }

    public void CopySheet( ) throws IOException {
        op.copyFileUsingApacheCommonsIO(workingDir ,System.getProperty("user.dir") );

    }


    public static boolean compareByCriteria(String criteria, int actualValue, double expectedValue) {

        boolean condition;

        switch (criteria.trim()) {
            case ">":
                condition = actualValue > expectedValue;
                break;
            case "<":
                condition = actualValue < expectedValue;
                break;
            case "==":
                condition = actualValue == expectedValue;
                break;
            case "=":
                condition = actualValue == expectedValue;
                break;
            case "<=":
                condition = actualValue <= expectedValue;
                break;
            case ">=":
                condition = actualValue >= expectedValue;
                break;

            default:
                throw new AssertionError("Unknown criteria value, expected: >, < or ==");
        }
        return condition;
    }

}
