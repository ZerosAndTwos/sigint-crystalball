package ae.pegasus.framework.json.serialization;

import ae.pegasus.framework.model.Target;
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

public class TargetGroupDeserializer extends JsonDeserializer<List<Target>> {
    @Override
    public List<Target> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        Iterator<JsonNode> iter = node.elements();
        List<Target> targets = new ArrayList<>();
        while (iter.hasNext()) {
            JsonNode nodeId = iter.next();
            String targetId = nodeId.textValue();

            Target target = Entities.getTargets().getEntity(targetId);
            targets.add(target);
        }
        return targets;
    }
}
