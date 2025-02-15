package ae.pegasus.framework.zapi.deserializer;


import ae.pegasus.framework.zapi.model.Cycle;
import ae.pegasus.framework.zapi.model.CyclesList;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class CyclesDeserialize extends JsonDeserializer<CyclesList> {


    @Override
    public CyclesList deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        CyclesList cycles = new CyclesList();
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        ObjectMapper mapper = new ObjectMapper();
        Iterator<Map.Entry<String, JsonNode>> iter = node.fields();
        Cycle cycle;
        while (iter.hasNext()) {
            try {
                Map.Entry<String, JsonNode> foundNode = iter.next();
                cycle = mapper.readValue(foundNode.getValue().asText(), Cycle.class);
                cycle.setId(foundNode.getKey());
                cycles.addtoCycle(cycle);
            } catch (JsonMappingException e) {
                continue;
            }
        }
        return cycles;
    }
}
