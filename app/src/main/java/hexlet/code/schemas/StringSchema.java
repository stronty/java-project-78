package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        super.required();
        return this;
    }


    public StringSchema minLength(int length) {
        addCheck("length", s -> s.length() >= length);
        return this;
    }

    public StringSchema contains(String regex) {
        addCheck("contains", s -> s.contains(regex));
        return this;
    }

    @Override
    public boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

}
