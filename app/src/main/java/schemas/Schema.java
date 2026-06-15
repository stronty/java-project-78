package schemas;

public abstract class Schema <SELF extends Schema<SELF, T>, T> {


    public abstract SELF required();
    public abstract boolean isValid(T content);

}
