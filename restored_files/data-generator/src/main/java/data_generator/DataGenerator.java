package data_generator;

import ae.pegasus.framework.data_for_entity.RandomEntities;
import data_for_entity.data_providers.data_target.SMSTextProvider;
import model.FSMS;
import model.G4Record;
import model.GenerationMatrix;
import model.Target;
import ae.pegasus.framework.utils.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator implements DataGeneratorService {

    private Class<?> aClass;
    private DataGeneratorService dataGeneratorService;

    public DataGenerator(Class<?> entityClass) {
        aClass = entityClass;
    }

    @Override
    public List<G4Record> produceListByMatrix(GenerationMatrix matrix) {

        if (aClass.equals(FSMS.class)) {
            dataGeneratorService = new FSMSGenerator();
        } else {
            throw new Error("Unknown G4Entity. Unable to initialize DataGenerator instance!");
        }

        return dataGeneratorService.produceListByMatrix(matrix);
    }

    public List produceList(int size) {
        return new RandomEntities().randomEntities(aClass, size);
    }

    public Object produce() {
        return new RandomEntities().randomEntity(aClass);
    }

    public Object produceSMSWithMention(String mention) {
        SMSTextProvider.setMention(mention);
        Object sms = new RandomEntities().randomEntity(aClass);
        SMSTextProvider.setMention("");
        return sms;
    }

    String getTargetPhone(Target target) {
        return RandomGenerator.getRandomItemFromList(new ArrayList<>(target.getPhones()));
    }
}
