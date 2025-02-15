package ae.pegasus.framework.data_for_entity.data_providers.data_target;

import ae.pegasus.framework.data_for_entity.data_providers.EntityDataProvider;
import ae.pegasus.framework.utils.RandomGenerator;

public class SMSTextProvider implements EntityDataProvider {

    private static String mention = "";

    public static void setMention(String mention) {
        SMSTextProvider.mention = mention;
    }

    @Override
    public Object generate(int length) {
        return RandomGenerator.generateSMSText(mention);
    }


}
