package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {

    @Override
    public MapSchema required() {
        super.required();

        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("has size", map -> map.size() == size);
        return this;
    }

    public boolean isEmpty(Map<?, ?> value) {
        return value == null || value.isEmpty();
    }

    public MapSchema shape(Map<?, ? extends BaseSchema<?>> schema) {
        Map<?, ? extends BaseSchema<?>> schemaCopy = new HashMap<>(schema);
        addCheck("shape", value -> schemaCopy.entrySet().stream()
                .allMatch(entry -> isEntryValid(entry, value)));
        return this;
    }

    private static boolean isEntryValid(
            Map.Entry<?, ? extends BaseSchema<?>> entry,
            Map<?, ?> value
    ) {
        return validate(entry.getValue(), value.get(entry.getKey()));
    }

    private static <T> boolean validate(BaseSchema<T> schema, Object value) {
        return schema.isValid((T) value);
    }
}
