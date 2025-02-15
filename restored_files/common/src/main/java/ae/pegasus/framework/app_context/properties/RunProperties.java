package ae.pegasus.framework.app_context.properties;

import java.io.InputStream;

public class RunProperties extends ApplicationProperty {
    public static String url = null;

    @Override
    InputStream loadPropertyFile() {
        return this.getClass().getResourceAsStream("/general.properties");
    }

    public boolean isRemoteRun() {
        return Boolean.parseBoolean(getProperty().getProperty("remoteRun"));
    }

    public String getApplicationURL() {
        if (url != null)
            return url;
        else
            return getProperty().getProperty("appURL");
    }

    public String getSeleniumHub() {
        return getProperty().getProperty("seleniumHub");
    }

    public String getWebBrowser() {
        return getProperty().getProperty("webBrowser");
    }

    public void seturl(String givenurl) {
        url = givenurl;
    }

    public boolean isSuppressKnownIssues() {
        return Boolean.parseBoolean(getProperty().getProperty("suppressKnownIssues"));
    }

    public int getLongTimeoutInMS() {
        return Integer.parseInt(getProperty().getProperty("timeout.long.ms", "60000"));
    }

    public int getSearchIndexPeriodInMS() {
        return Integer.parseInt(getProperty().getProperty("searchIndex.updatePeriod.ms", "5000"));
    }

    public String getTimeZoneId() {
        return getProperty().getProperty("timeZoneId");
    }

}
