package ae.pegasus.framework.data_generators;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberType;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import org.apache.commons.lang3.RandomStringUtils;

public class PhoneNumberGenerator extends BasicGenerator {

    private static String[] regions = null;

    public static String getPhoneNumber() {
        if (regions == null) {
         regions = PhoneNumberUtil.getInstance().getSupportedRegions().toArray(new String[1]);
        }
        String regionCode = regions[getRandomIndexFor(regions)];

        PhoneNumberType[] numberTypes = PhoneNumberUtil.getInstance().getSupportedTypesForRegion(regionCode).toArray(new PhoneNumberUtil.PhoneNumberType[1]);
        PhoneNumberType phoneNumberType = numberTypes[getRandomIndexFor(numberTypes)];

        PhoneNumber numberExample = PhoneNumberUtil.getInstance().getExampleNumberForType(regionCode, phoneNumberType);
        int numberOfDigitsInNumber = String.valueOf(numberExample.getNationalNumber()).length();

        return PhoneNumberUtil.getInstance().getCountryCodeForRegion(regionCode) + RandomStringUtils.randomNumeric(numberOfDigitsInNumber);
    }
}
