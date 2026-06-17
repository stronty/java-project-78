package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    private int length;
    private String regex;
    private boolean hasRegex;
    private boolean hasLength;

    public StringSchema() {

    }

    @Override
    public StringSchema required() {
        required = true;
        return this;
    }

    public StringSchema minLength(int length) {
        hasLength = true;
        this.length = length;
        return this;
    }

    public StringSchema contains(String regex) {
        this.regex = regex;
        hasRegex = true;
        return this;
    }

    @Override
    public boolean isValid(String content) {

        if (required && (content == null || content.isEmpty())) {
            return false;
        } else if (content == null || content.isEmpty()) {
            return  true;
        }
        if (content.length() < length) {
            return false;
        }
        if (hasRegex && !content.contains(regex)) {
            return false;
        }

        return true;

    }

}
