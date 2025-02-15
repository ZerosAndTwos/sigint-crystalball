package ae.pegasus.framework.json.serialization;

import ae.pegasus.framework.model.TargetGroup;
import ae.pegasus.framework.model.entities.Entities;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TargetDeserializer extends JsonDeserializer<List<TargetGroup>> {
    @Override
    public List<TargetGroup> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        Iterator<JsonNode> iter = node.elements();
        List<TargetGroup> targetGroups = new ArrayList<>();
        while (iter.hasNext()) {
            JsonNode groupNode = iter.next();
            String targetGroupId = groupNode.get("id").textValue();

            TargetGroup targetGroup = Entities.getTargetGroups().getEntity(targetGroupId);
            targetGroups.add(targetGroup);
        }
        return targetGroups;
    }
}
