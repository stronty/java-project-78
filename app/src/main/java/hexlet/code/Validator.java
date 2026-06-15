package hexlet.code;

import schemas.MapSchema;
import schemas.NumberSchema;
import schemas.StringSchema;

public class Validator {

    public static StringSchema string() {
        return new StringSchema();
    }
    public static NumberSchema number() {
        return new NumberSchema();
    }

    public static MapSchema map() {
        return new MapSchema();
    }
}
