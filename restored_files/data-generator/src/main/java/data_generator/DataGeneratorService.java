package data_generator;

import model.G4Record;
import model.GenerationMatrix;

import java.util.List;

interface DataGeneratorService {

    List<G4Record> produceListByMatrix(GenerationMatrix matrix);

}
