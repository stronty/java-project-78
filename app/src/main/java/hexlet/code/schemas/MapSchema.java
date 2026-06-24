package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {

    private int size;
    private boolean hasStrictSize;
    private boolean checkInside;
    private Map<?, BaseSchema<?>> schema;

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

    public void shape(Map<?, ? extends BaseSchema<?>> schema) {
        checkInside = true;
        this.schema = new HashMap<>(schema);
    }

    private static boolean validate(BaseSchema<?> baseSchema, Object value) {
        return ((BaseSchema<Object>) baseSchema).isValid(value);
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
            for (Map.Entry<?, BaseSchema<?>> entry : schema.entrySet()) {
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
