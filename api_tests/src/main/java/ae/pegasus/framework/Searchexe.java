package ae.pegasus.framework;

import ae.pegasus.framework.app_context.properties.RunProperties;
import ae.pegasus.framework.steps.APILoginSteps;
import ae.pegasus.framework.steps.APISearchSteps;

public class Searchexe {

    public static String url = null;
    public static String username = "admin@pegasus.ae";
    public static String password = "123456";
    public static String excelpath = "/Data/Pair_wise_testing.xlsx";

    public static void main(String[] args) {
        if (args.length != 0) {
            if (args[0].length() != 0)
                url = args[0];
            if (args[1].length() != 0)
                username = args[1];
            if (args[2].length() != 0)
                password = args[2];
            if (args[3].length() != 0)
                excelpath = args[3];
        }

            RunProperties prop = new RunProperties();
            prop.seturl(url);
            APILoginSteps login = new APILoginSteps();
            login.signInGlobal();
            APISearchSteps Search = new APISearchSteps();
            Search.ExcelDrivenSearch(excelpath);

    }

}
