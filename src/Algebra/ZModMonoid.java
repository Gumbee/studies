package Algebra;

import java.util.ArrayList;

/**
 * Created by mugeebhassan on 04/12/16.
 */
public class ZModMonoid implements Monoid<Integer> {

    // contains all elements of the set of this monoid
    protected ArrayList<Integer> set;
    // all operations on this set are calculated modulo "mod"
    protected int mod;
    // specifies which type of operation is to be used for this instance (since we have
    // defined multiple possible operations, like addition and multiplication)
    protected ZModType type;

    public ZModMonoid(int mod, ZModType type){
        this.mod = mod;
        this.type = type;

        set = new ArrayList<>();

        for(int i=0;i<mod;i++){
            set.add(i);
        }
    }

    @Override
    public Integer[] set() {
        Integer[] arraySet = set.toArray(new Integer[set.size()]);

        return arraySet;
    }

    @Override
    public Integer add(Integer A, Integer B) {
        A = getElement(A);
        B = getElement(B);

        // since we defined two different operations for this group(add and multiply) we have to specify
        // which operation this instance is using and then output the value accordingly
        switch (type){
            case additive:
                return addNumbers(A, B);
            case multiplicative:
                return multiplyNumbers(A, B);
            default:
                throw new RuntimeException("No type found in ZModGroup.");
        }
    }

    @Override
    public Integer identity() {
        switch (type){
            case additive:
                return 0;
            case multiplicative:
                return 1;
            default:
                throw new RuntimeException("No type found in ZModGroup.");
        }
    }

    /*==========================================
     * Custom Methods
     ===========================================*/

    protected final Integer getElement(Integer A){
        // checks if the desired element is part of the legal set and throws an error otherwise
        for(int e:set){
            if(e==A){
                return A;
            }
        }

        throw new NullPointerException(A + " is not element of this set!");
    }

    /**
     * returns the additive inverse of an element
     */
    private final Integer addNumbers(Integer A, Integer B){
        return Math.floorMod((A+B), mod);
    }

    /**
     * returns the multiplicative inverse of an element
     */
    private final Integer multiplyNumbers(Integer A, Integer B){
        return Math.floorMod((A*B),mod);
    }

}
