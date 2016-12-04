package Algebra;

/**
 * Created by mugeebhassan on 04/12/16.
 */
public class Ring<T> {

    Group<T> additive;
    Monoid<T> multiplicative;

    public Ring(Group<T> additive, Monoid<T> multiplicative){
        this.additive = additive;
        this.multiplicative = multiplicative;
    }

    /**
     * performs an addition and returns the value
     */
    public final T add(T A, T B){
        return additive.add(A, B);
    }

    /**
     * performs a "multiplication" and returns the value
     */
    public final T mult(T A, T B){
        return multiplicative.add(A, B);
    }

    /**
     * returns the additive inverse
     */
    public final T additiveInverse(T A){
        return additive.inverse(A);
    }

    /**
     * returns the additive identity
     */
    public final T additiveIdentity(){
        return additive.identity();
    }

    /**
     * returns the multiplicative identity
     */
    public final T multiplicativeIdentity(){
        return multiplicative.identity();
    }

}
