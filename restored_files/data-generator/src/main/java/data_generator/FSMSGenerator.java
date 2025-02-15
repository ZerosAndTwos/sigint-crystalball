package data_generator;


import model.FSMS;
import model.G4Record;
import model.GenerationMatrix;
import model.GenerationMatrixRow;
import ae.pegasus.framework.utils.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

class FSMSGenerator extends DataGenerator {

    FSMSGenerator() {
        super(FSMS.class);
    }

    @Override
    public List<G4Record> produceListByMatrix(GenerationMatrix generationMatrix) {
        List<G4Record> list = new ArrayList<>();

        /*
            Generate F-SMS list for each target from GenerationMatrix,
            according to the current Generation matrix row with target and 'from/to' target records,
            or any mention about this target parameters for him
         */
        for (GenerationMatrixRow row : generationMatrix.getRows()) {
            String phone = getTargetPhone(row.getTarget());
            FSMS fsms;

            // generate F-SMS list 'from' current target phone
            int from = 0;
            while (from < row.getFromNumberCount()) {
                fsms = (FSMS) produce();
                fsms.setFromNumber(phone);
                fsms.setCallingGlobalTitle(phone);
                list.add(fsms);
                from++;
            }

            // generate F-SMS list 'to' current target phone
            int to = 0;
            while (to < row.getToNumberCount()) {
                fsms = (FSMS) produce();
                fsms.setToNumber(phone);
                fsms.setTargetNumber(phone);
                list.add(fsms);
                to++;
            }

            // generate F-SMS list with 'target phone' mention in the text message
            int fromMention = 0;
            while (fromMention < row.getNumberMention()) {
                fsms = (FSMS) produceSMSWithMention(phone);
                list.add(fsms);
                fromMention++;
            }

            // generate F-SMS list with 'target keyword' mention in the text message
            int keywordMention = 0;
            while (keywordMention < row.getKeywordMention()) {
                List<String> keywords = new ArrayList<>(row.getTarget().getKeywords());
                String mention = RandomGenerator.getRandomItemFromList(keywords);

                fsms = (FSMS) produceSMSWithMention(mention);
                list.add(fsms);
                keywordMention++;
            }

            // generate F-SMS list with 'target name' mention in the text message
            int nameMention = 0;
            while (nameMention < row.getNameMention()) {
                String mention = row.getTarget().getName();

                fsms = (FSMS) produceSMSWithMention(mention);
                list.add(fsms);
                nameMention++;
            }

            // generate randomly F-SMS list without any target mention in the text message
            int withoutHitMention = 0;
            while (withoutHitMention < row.getWithoutHitMention()) {
                fsms = (FSMS) produce();
                list.add(fsms);
                withoutHitMention++;
            }
        }

        return list;
    }

}
