package ae.pegasus.framework.constants;

public class CommonXPaths {
    private static final String INFINITE_WAIT_XPATH = "//span[contains(@class,'text-muted') and contains(., '%s')]";

    //Loadings
    public static final String LOADING_BASE_XPATH = String.format(INFINITE_WAIT_XPATH, "Loading...");
    public static final String PAGE_LOADING_XPATH =  LOADING_BASE_XPATH;
    public static final String INTERNAL_LOADING_XPATH = "." + LOADING_BASE_XPATH;

    //Saving
    public static final String INTERNAL_SAVING_XPATH = "." + String.format(INFINITE_WAIT_XPATH, "Saving...");

    public static final String DROP_CONTENT_BASE_XPATH = "//body/div[contains(@class, 'pg-drop__content')]";
}
