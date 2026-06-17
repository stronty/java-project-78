package schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends Schema<Map<?, ?>> {

    private int size;
    private boolean hasStrictSize;
    private boolean checkInside;
    private Map<?, Schema<?>> schema;

    @Override
    public MapSchema required() {
        required = true;
        return this;
    }

    public MapSchema sizeof(int size) {
        hasStrictSize = true;
        this.size = size;

        return this;
    }

    public void shape(Map<?, ? extends Schema<?>> schema) {
        checkInside = true;
        this.schema = new HashMap<>(schema);
    }

    private static boolean validate(Schema<?> schema, Object value) {
        return ((Schema<Object>) schema).isValid(value);
    }

    @Override
    public boolean isValid(Map content) {
        if (required && (content == null ||  content.isEmpty())) {
            return false;
        }
        if (hasStrictSize && content.size() != size) {
            return false;
        }
        if (checkInside) {
            for (Map.Entry<?, Schema<?>> entry : schema.entrySet()) {
                var key = entry.getKey();
                Object value = content.get(key);

                if (!validate(schema.get(key), value)) {
                    return false;
                }
            }
        }
        return true;
    }
}
