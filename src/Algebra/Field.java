package Algebra;

import java.util.ArrayList;

/**
 * Created by mugeebhassan on 06/12/16.
 */
public interface Field<T> extends Ring<T>{

    /**
     * performs a "multiplication" and returns the value
     */
    public T div(T A, T B);

    /**
     * returns the additive inverse
     */
    public T multiplicativeInverse(T A);

    public Group<T> getMultiplicativeGroup();
}

interface Ring<T> {

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

interface Group<T> extends Monoid<T> {

    // returns the inverse of element A
    T inverse(T A);

}

interface Monoid<T> {

    // returns the set of elements that make up this monoid
    ArrayList<T> getSet();

    // returns the value of the binary operation T x T -> T that satisfies the following rule: (a • b) • c = a • (b • c)
    T add(T A, T B);

    // returns the identity element e sucht that for every a following holds: (a • e) = (e • a) = a
    T identity();

}
