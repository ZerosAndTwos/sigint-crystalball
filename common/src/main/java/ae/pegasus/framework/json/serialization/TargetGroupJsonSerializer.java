package ae.pegasus.framework.json.serialization;

import ae.pegasus.framework.model.Target;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class TargetGroupJsonSerializer extends JsonSerializer<List<Target>> {
    @Override
    public void serialize(List<Target> value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeStartArray();
        for (Target target:value){
            jgen.writeString(target.getId());
        }
        jgen.writeEndArray();

    }
}
