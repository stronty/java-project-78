package schemas;

public class StringSchema extends Schema<StringSchema, String> {
    private boolean notNull;
    private int length;
    private String REGX;
    private boolean hasREGX;

    public StringSchema() {

    }

    @Override
    public StringSchema required() {
        notNull = true;
        return this;
    }

    public StringSchema minLength(int length) {
        this.length = length;
        return this;
    }

    public StringSchema contains(String mustContainREGX) {
        this.REGX = mustContainREGX;
        hasREGX = true;
        return this;
    }

    @Override
    public boolean isValid(String content) {

        if(notNull && content.isEmpty()) {
            return false;
        }
        if(content.length() < length) {
            return false;
        }
        if(hasREGX && content.contains(REGX)) {
            return false;
        }

        return true;

    }

}
