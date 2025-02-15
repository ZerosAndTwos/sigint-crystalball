package ae.pegasus.framework.services;

import ae.pegasus.framework.model.SSMS;
import org.apache.commons.lang3.RandomStringUtils;
import ae.pegasus.framework.utils.RandomGenerator;

import java.util.*;

public class SSMSCreator {

    public static SSMS generateSSMS() {

        Calendar calendar = Calendar.getInstance();
        int seconds = new Random().nextInt(3600);
        calendar.add(Calendar.HOUR, -3); // "GMT+03:00"
        calendar.add(Calendar.SECOND, -seconds); // last hour

        SSMS ssms = new SSMS();
        ssms
                .setCdr_id(Integer.valueOf(RandomStringUtils.randomNumeric(8)))
                .setEvent_time(calendar.getTime())
                .setUnit(0)
                .setMatrix_ts(Integer.valueOf(RandomStringUtils.randomNumeric(4)))
                .setNi(0)
                .setOpc(Integer.valueOf(RandomStringUtils.randomNumeric(6)))
                .setDpc(Integer.valueOf(RandomStringUtils.randomNumeric(6)))
                .setHit_count(0)
                .setMaskmarked(0)
                .setSrcip("")
                .setDscip("")
                .setSms_time(calendar.getTime())
                .setSmstimezone("GMT+03:00");

        ssms
                .setTxt(RandomGenerator.generateSMSText(false))
                .setHaveparts(Integer.valueOf(RandomStringUtils.randomNumeric(3)))
                .setTotalparts(Integer.valueOf(RandomStringUtils.randomNumeric(3)));

        String calledIMSI = RandomGenerator.generateIMSI();
        ssms
                .setCalled_imsi(calledIMSI)
                .setCalled_imsi_cc(calledIMSI.substring(0, 3))
                .setCalled_imsi_ac(RandomStringUtils.randomNumeric(1))
                .setCalled_imsi_hit("");

        String callerIMSI = RandomGenerator.generateIMSI();
        ssms
                .setCaller_imsi(callerIMSI)
                .setCaller_imsi_cc(callerIMSI.substring(0, 3))
                .setCaller_imsi_ac(RandomStringUtils.randomNumeric(1))
                .setCaller_imsi_hit("")
                .setPattern("");

        String toNumber = RandomGenerator.generatePhone();
        ssms
                .setCalled(toNumber)
                .setCalled_non(1)
                .setToNumber(toNumber);

        List<String> list = new ArrayList<>(Arrays.asList("", "imsi"));
        ssms
                .setCalled_rule(RandomGenerator.getRandomItemFromList(list))
                .setCalled_hit("")
                .setCalled_cc("")
                .setCalled_ac("");

        String fromNumber = RandomGenerator.generatePhone();
        ssms
                .setCaller(fromNumber)
                .setCaller_non(1)
                .setCaller_rule("")
                .setCaller_hit("")
                .setCaller_cc("")
                .setCaller_ac("")
                .setFromNumber(fromNumber);

        String ssmsNumber = RandomGenerator.generatePhone();
        ssms
                .setSmsc(ssmsNumber)
                .setSmsc_non(1)
                .setSmsc_mod(ssmsNumber)
                .setSmsc_rule("")
                .setSmsc_hit("")
                .setSmsc_cc("")
                .setSmsc_ac("");

        return ssms;
    }
}
