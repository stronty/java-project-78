package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {

    private boolean positive;
    private int min;
    private int max;
    private boolean hasRange;

    public NumberSchema() {

    }

    @Override
    public NumberSchema required() {
        required = true;

        return this;
    }

    public NumberSchema positive() {
        positive = true;

        return this;
    }

    public NumberSchema range(int min, int max) {
        this.min = min;
        this.max = max;
        hasRange = true;

        return this;
    }

    @Override
    public boolean isValid(Integer num) {
        if (required && (num == null || num == 0)) {
            return false;
        } else if (num == null) {
            return true;
        }
        if (positive && num < 0) {
            return false;
        }
        if (hasRange && (num < min || num > max)) {
            return false;
        }

        return true;

    }

}
