package data_generator;

import data_for_entity.ObjectInitializer;
import data_for_entity.data_providers.SMSTextProvider;
import model.GenerationMatrix;
import model.Target;
import model.XSMS;
import model.XVoiceMetadata;
import utils.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator implements DataGeneratorService {

    private Class<?> aClass;
    private DataGeneratorService dataGeneratorService;

    public DataGenerator(Class<?> entityClass) {
        aClass = entityClass;
    }

    @Override
    public List produceListByMatrix(GenerationMatrix matrix) {

        if (aClass.equals(XSMS.class)) {
            dataGeneratorService = new XSMSGenerator();
        } else if (aClass.equals(XVoiceMetadata.class)) {
            dataGeneratorService = new XVoiceMetadataGenerator();
        } else {
            throw new Error("Unknown TeelaEntity. Unable to initialize DataGenerator instance!");
        }

        return dataGeneratorService.produceListByMatrix(matrix);
    }

    public List produceList(int size) {
        return new ObjectInitializer().generateObjects(aClass, size);
    }

    public Object produce() {
        return new ObjectInitializer().generateObject(aClass);
    }

    public Object produceSMSWithMention(String mention) {
        SMSTextProvider.setMention(mention);
        Object sms = new ObjectInitializer().generateObject(aClass);
        SMSTextProvider.setMention("");
        return sms;
    }

    String getTargetPhone(Target target) {
        return RandomGenerator.getRandomItemFromList(new ArrayList<>(target.getPhones()));
    }
}
