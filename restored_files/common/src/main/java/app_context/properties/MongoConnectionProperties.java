package app_context.properties;

import app_context.Stand;
import error_reporter.ErrorReporter;
import org.apache.log4j.Logger;

import java.util.Arrays;

public class MongoConnectionProperties {

    private static Logger log = Logger.getLogger(MongoConnectionProperties.class);

    public static String getHost() {
        Stand stand = Stand.TEST;

        String standString = G4Properties.getRunProperties().getActiveStand();
        try {
            stand = Stand.valueOf(standString.toUpperCase());
        } catch (IllegalArgumentException e) {
            ErrorReporter.reportAndRaiseError(String.format("Unknown stand is used: %s!." +
                    "Should be between %s", standString, Arrays.toString(Stand.values())));
        }

        String mongoHost = null;
        switch (stand) {
            case TEST:
                mongoHost = G4Properties.getMongoProperties().getHostTest();
                break;
            case DEVELOP:
                mongoHost = G4Properties.getMongoProperties().getHostDevelop();
                break;
            case MASTER:
                mongoHost = G4Properties.getMongoProperties().getHostMaster();
                break;
            default:
                ErrorReporter.raiseError("Unknown stand is passed:" + standString);
                break;
        }
        return mongoHost;
    }

    public static String getPort() {
        return G4Properties.getMongoProperties().getPort();
    }
}
