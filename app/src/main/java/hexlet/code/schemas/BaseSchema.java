package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema<T> {
    protected boolean required;
    Map<String, Predicate<T>> checks = new LinkedHashMap<>();

    public BaseSchema<T> required() {
        required = true;
        return this;
    }

    public boolean isValid(T value) {
        if (isEmpty(value)) {
            return !required;
        }
        return checks.values().stream().allMatch(check -> check.test(value));
    }


    public boolean isEmpty(T value) {
        return value == null;
    }

    public void addCheck(String name, Predicate<T> check) {
        checks.put(name, check);
    }


}
