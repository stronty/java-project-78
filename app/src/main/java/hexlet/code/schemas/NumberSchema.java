package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {

    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }


    public NumberSchema positive() {
        addCheck("positive", num -> num > 0);
        return  this;
    }

    public NumberSchema range(int min, int max) {
        addCheck("range", num -> num >= min && num <= max);
        return this;
    }

}
