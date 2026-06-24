
import hexlet.code.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.StringSchema;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShemasTests {

    private final Validator v = new Validator();
    private NumberSchema numSchema;
    private StringSchema stringSchema;

    private MapSchema mapSchema;
    private Map<String, BaseSchema<Integer>> numSchemas;
    private Map<String, BaseSchema<String>> stringSchemas;

    @BeforeEach
    void prepare() {
        numSchema = v.number();
        stringSchema = v.string();
        mapSchema = v.map();
        numSchemas = new HashMap<String, BaseSchema<Integer>>();
        stringSchemas = new HashMap<String, BaseSchema<String>>();
    }

    @Test
    void mapSchemaRequiredTest() {
        assertTrue(mapSchema.isValid(stringSchemas));
        assertFalse(mapSchema.required().isValid(stringSchemas));
    }
    @Test
    void mapSchemaSizeTest() {
        Map<Integer, Integer> numbers = Map.of(1, 1, 2, 2, 3, 3, 4, 4);

        assertTrue(mapSchema.sizeof(4).isValid(numbers));
        assertFalse(mapSchema.sizeof(0).isValid(numbers));
        assertFalse(mapSchema.sizeof(5).isValid(numbers));
    }
    @Test
    void mapSchemaCheclInsideTest() {
        stringSchemas.put("firstName", v.string().required());
        stringSchemas.put("lastName", v.string().required().minLength(2));

        mapSchema.shape(stringSchemas);


        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);

        assertFalse(mapSchema.isValid(human2));


        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(mapSchema.isValid(human1)); // true

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");

        assertFalse(mapSchema.isValid(human3)); // false
    }


    @Test
    void integerSchemaNullTest() {
        assertTrue(numSchema.isValid(null));
        assertTrue(numSchema.positive().isValid(null));
    }

    @Test
    void integerSchemaRequiredTest() {
        assertFalse(numSchema.required().isValid(null));
        assertTrue(numSchema.required().isValid(1));
    }

    @Test
    void integerSchemaPositiveTest() {
        assertTrue(numSchema.positive().isValid(12));
        assertFalse(numSchema.isValid(-12));
    }

    @Test
    void integerSchemaRangeTest() {
        assertTrue(numSchema.range(5, 10).isValid(5));
        assertTrue(numSchema.isValid(10));
        assertFalse(numSchema.isValid(4));
        assertFalse(numSchema.isValid(11));
    }
    @Test
    void stringSchemaNullTest() {
        assertTrue(stringSchema.isValid(""));
        assertTrue(stringSchema.isValid(null));
        assertFalse(stringSchema.required().isValid(null));
        assertTrue(stringSchema.required().isValid("not null"));
    }

    @Test
    void stringSchemaNoConditionTest() {
        assertTrue(stringSchema.isValid("Hello"));
        assertTrue(stringSchema.isValid("World"));
    }

    @Test
    void stringSchemaContainsTest() {
        assertTrue(stringSchema.contains("He").isValid("Hello"));
        assertFalse(stringSchema.contains("qerqw").isValid("World"));
        assertFalse(stringSchema.isValid("World"));
    }

    @Test
    void stringSchemasTest() {
        assertTrue(stringSchema.minLength(5).isValid("Hello"));
        assertTrue(stringSchema.minLength(0).isValid("Hello"));
        assertFalse(stringSchema.minLength(10).isValid("World"));
        assertFalse(stringSchema.isValid("World"));
    }

}
