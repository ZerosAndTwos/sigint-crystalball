package ae.pegasus.framework.json.serialization;

import ae.pegasus.framework.model.TargetGroup;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class TargetJsonSerializer extends JsonSerializer<List<TargetGroup>> {
    public void serialize(List<TargetGroup> value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeStartArray();
        for (TargetGroup group: value) {
            jgen.writeStartObject();
            jgen.writeObjectField("id", group.getId());
            jgen.writeEndObject();
        }
        jgen.writeEndArray();

    }
}
