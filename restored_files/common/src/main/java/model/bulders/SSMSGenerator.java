package model.bulders;

import model.*;
import utils.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * S-SMS data generator.
 */
public class SSMSGenerator {

    /**
     * S-SMS Builder
     */
    private SSMSBuilder ssmsBuilder;

    /**
     * S-SMS data generator.
     * By default used SSMSRandom builder.
     */
    public SSMSGenerator() {
        ssmsBuilder = new SSMSRandom();
    }

    /**
     * Set SSMSRandom S-SMS builder
     *
     * @return SSMSGenerator
     */
    private SSMSGenerator random() {
        ssmsBuilder = new SSMSRandom();
        return this;
    }

    /**
     * Set SSMSToNumber S-SMS builder
     *
     * @param number target number
     * @return SSMSGenerator
     */
    private SSMSGenerator toNumber(String number) {
        ssmsBuilder = new SSMSToNumber(number);
        return this;
    }

    /**
     * Set SSMSFromNumber S-SMS builder
     *
     * @param number target number
     * @return SSMSGenerator
     */
    private SSMSGenerator fromNumber(String number) {
        ssmsBuilder = new SSMSFromNumber(number);
        return this;
    }

    /**
     * Set SSMSWithTextMention S-SMS builder
     *
     * @param pattern target mention string (Target name, phone or keyword)
     * @return SSMSGenerator
     */
    private SSMSGenerator withTextMention(String pattern) {
        ssmsBuilder = new SSMSWithTextMention(pattern);
        return this;
    }

    /**
     * Produce one S-SMS, according SSMSBuilder
     *
     * @return S-SMS
     */
    public SSMS produce() {
        ssmsBuilder.createNewSSMS();
        ssmsBuilder.buildFromNumber();
        ssmsBuilder.buildToNumber();
        ssmsBuilder.buildText();

        return ssmsBuilder.getSSMS();
    }

    /**
     * Produce List of S-SMS according GenerationMatrix
     *
     * @return List of S-SMS
     */
    public List<G4Record> produceSSMSListByMatrix(GenerationMatrix generationMatrix) {
        List<G4Record> ssmsList = new ArrayList<>();

        /*
            Generate S-SMS list for each target from GenerationMatrix,
            according to the current Generation matrix row with target and 'from/to' target records,
            or any mention about this target parameters for him
         */
        for (GenerationMatrixRow row : generationMatrix.getRows()) {
            String phone = getTargetPhone(row.getTarget());

            // generate S-SMS list 'from' current target phone
            int from = 0;
            while (from < row.getFromNumberCount()) {
                ssmsList.add(fromNumber(phone).produce());
                from++;
            }

            // generate S-SMS list 'to' current target phone
            int to = 0;
            while (to < row.getToNumberCount()) {
                ssmsList.add(toNumber(phone).produce());
                to++;
            }

            // generate S-SMS list with 'target phone' mention in the text message
            int fromMention = 0;
            while (fromMention < row.getNumberMention()) {
                ssmsList.add(withTextMention(phone).produce());
                fromMention++;
            }

            // generate S-SMS list with 'target keyword' mention in the text message
            int keywordMention = 0;
            while (keywordMention < row.getKeywordMention()) {
                List<String> keywords = new ArrayList<>(row.getTarget().getKeywords());
                String mention = RandomGenerator.getRandomItemFromList(keywords);

                ssmsList.add(withTextMention(mention).produce());
                keywordMention++;
            }

            // generate S-SMS list with 'target name' mention in the text message
            int nameMention = 0;
            while (nameMention < row.getNameMention()) {
                ssmsList.add(withTextMention(row.getTarget().getName()).produce());
                nameMention++;
            }

            // generate randomly S-SMS list without any target mention in the text message
            int withoutHitMention = 0;
            while (withoutHitMention < row.getWithoutHitMention()) {
                ssmsList.add(random().produce());
                withoutHitMention++;
            }
        }

        return ssmsList;
    }

    /**
     * Generate List of random S-SMS.
     *
     * @param count S-SMS count
     * @return List of S-SMS
     */
    public List<SSMS> produceSSMSListRandomly(int count) {
        List<SSMS> ssmsEntityList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            ssmsEntityList.add(random().produce());
        }
        return ssmsEntityList;
    }

    private String getTargetPhone(Target target) {
        return RandomGenerator.getRandomItemFromList(new ArrayList<>(target.getPhones()));
    }
}
