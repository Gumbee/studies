package Algebra;

import java.io.UncheckedIOException;
import java.util.ArrayList;

/**
 * Created by mugeebhassan on 04/12/16.
 */
public interface Ring<T> {

    /**
     * performs an addition and returns the value
     */
    public T add(T A, T B);

    /**
     * performs a subtraction (addition with additive inverse) and returns the value (subtract A from B)
     */
    public T sub(T A, T B);

    /**
     * performs a "multiplication" and returns the value
     */
    public T mult(T A, T B);

    /**
     * returns the additive inverse
     */
    public T additiveInverse(T A);

    /**
     * returns the additive identity
     */
    public T additiveIdentity();

    /**
     * returns the multiplicative identity
     */
    public T multiplicativeIdentity();

    public Group<T> getAdditiveGroup();

    public Monoid<T> getMultiplicativeMonoid();

    public ArrayList<T> getSet();

}
