package hexlet.code; // main package

import schemas.Schema;

import java.util.HashMap;
import java.util.Map;

class Main {
    void main() {


        var v = new Validator();

        var schema = v.map();

        Map<String, Schema<String>> schemas = new HashMap<>();

        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);
        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        schema.isValid(human1); // true

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        schema.isValid(human2); // false

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        schema.isValid(human3); // false
    }
}
