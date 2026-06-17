package schemas;

public abstract class Schema<T> {
    protected boolean required;

    public abstract Schema<T> required();
    public abstract boolean isValid(T content);

}
