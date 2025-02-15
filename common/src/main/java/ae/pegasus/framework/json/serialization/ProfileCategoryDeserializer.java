package ae.pegasus.framework.json.serialization;

import ae.pegasus.framework.model.ProfileCategory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class ProfileCategoryDeserializer extends JsonDeserializer<ProfileCategory> {

    @Override
    public ProfileCategory deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        JsonNode node = jp.getCodec().readTree(jp);
        String displayName = node.textValue();

        for (ProfileCategory category : ProfileCategory.values()) {
            if (category.getDisplayName().equals(displayName)) {
                return category;
            }
        }
        return null;
    }

}
