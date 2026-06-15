package schemas;

import java.util.Map;

public class MapSchema extends Schema<MapSchema, Map>{
    private boolean notNull;
    private int size;
    private boolean hasStrictSize;

    @Override
    public MapSchema required() {
        notNull = true;
        return this;
    }

    public MapSchema sizeof(int size) {
        hasStrictSize = true;
        this.size = size;

        return this;
    }

    @Override
    public boolean isValid(Map content) {
        if (notNull && content == null) {
            return false;
        } else if (content == null) {
            return true;
        }
        if(hasStrictSize && content.size() != size) {
            return false;
        }
        return true;
    }
}
