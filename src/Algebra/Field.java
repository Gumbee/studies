package Algebra;

/**
 * Created by mugeebhassan on 06/12/16.
 */
public interface Field<T> extends Ring<T>{

    /**
     * returns the additive inverse
     */
    public T multiplicativeInverse(T A);

    public Group<T> getMultiplicativeGroup();
}
