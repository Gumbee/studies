package Algebra;

import java.io.UncheckedIOException;

/**
 * Created by mugeebhassan on 04/12/16.
 */
public class Ring<T> {

    protected Group<T> additive;
    protected Monoid<T> multiplicative;

    public Ring(Group<T> additive, Monoid<T> multiplicative){
        this.additive = additive;
        this.multiplicative = multiplicative;

        if(additive.set().length != multiplicative.set().length){
            throw new RuntimeException("The additive group must operate on the same set as the multiplicative group!");
        }

        for(int i=0;i<additive.set().length;i++){
            if(additive.set()[i] != multiplicative.set()[i]){
                throw new RuntimeException("The additive group must operate on the same set as the multiplicative group!");
            }
        }
    }

    /**
     * performs an addition and returns the value
     */
    public T add(T A, T B){
        return additive.add(A, B);
    }

    /**
     * performs a "multiplication" and returns the value
     */
    public T mult(T A, T B){
        return multiplicative.add(A, B);
    }

    /**
     * returns the additive inverse
     */
    public T additiveInverse(T A){
        return additive.inverse(A);
    }

    /**
     * returns the additive identity
     */
    public T additiveIdentity(){
        return additive.identity();
    }

    /**
     * returns the multiplicative identity
     */
    public T multiplicativeIdentity(){
        return multiplicative.identity();
    }

}
