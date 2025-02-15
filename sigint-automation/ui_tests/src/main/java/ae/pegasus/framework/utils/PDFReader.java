package ae.pegasus.framework.utils;

import ae.pegasus.framework.assertion.Asserter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class PDFReader {


    public static  void validatePDF(String filename , String valueToValidate) {


        getChekIfPDFContainsValues(getPDFText(getPDFPath(filename)),valueToValidate.split(","));

    }

    private static  String getPDFPath(String filename) {
        return (System.getProperty("user.dir") + "/PDF/Output/" +filename +"/" + filename + ".pdf") ;

    }


    private static  void getChekIfPDFContainsValues(String[] actualValues, String[] expectedValues) {

        for (String value : expectedValues)
        {

            Asserter.getAsserter().softAssertTrue(
                    Arrays.asList(actualValues).contains(value),
                    value + "Is present in the downlode PDF report",
                    value + "Is not present in the downlode PDF report");
        }
    }

    public static  String[] getPDFText(String path){
        String lines[] = null;
        try (PDDocument document = PDDocument.load(new File(path))) {

            document.getClass();

            if (!document.isEncrypted()) {

                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);

                PDFTextStripper tStripper = new PDFTextStripper();

                String pdfFileInText = tStripper.getText(document);
                //System.out.println("Text:" + st);
                tStripper.getArticleStart();

                // split by whitespace
                 lines = pdfFileInText.split("\\r?\\n");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
return lines;

    }

    public static String getDownloadedReportPath()
    {
return (System.getProperty("user.dir") + "/PDF");
    }
}
