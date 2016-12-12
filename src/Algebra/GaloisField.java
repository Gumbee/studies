package Algebra;

import java.util.ArrayList;

/**
 * Created by mugeebhassan on 08/12/16.
 */
public class GaloisField<T> implements Field<T> {

    private Group<T> additive;
    private Group<T> multiplicative;

    public GaloisField(Group<T> additive, Group<T> multiplicative){
        this.additive = additive;
        this.multiplicative = multiplicative;

        System.out.println("The multiplicative set has a size of " + multiplicative.getSet().size());

        // check if the defined set that the field operates on is valid
        checkSet(additive, multiplicative);
    }

    @Override
    public T add(T A, T B) {
        return additive.add(A, B);
    }

    @Override
    public T sub(T A, T B) {
        return add(B, additiveInverse(A));
    }

    @Override
    public T mult(T A, T B) {
        return multiplicative.add(A, B);
    }

    @Override
    public T additiveInverse(T A) {
        return additive.inverse(A);
    }

    @Override
    public T additiveIdentity() {
        return additive.identity();
    }

    @Override
    public T multiplicativeIdentity() {
        return multiplicative.identity();
    }

    @Override
    public T multiplicativeInverse(T A) {
        return multiplicative.inverse(A);
    }

    @Override
    public Group<T> getAdditiveGroup() {
        return additive;
    }

    @Override
    public Monoid<T> getMultiplicativeMonoid() {
        return getMultiplicativeGroup();
    }

    @Override
    public ArrayList<T> getSet() {
        return multiplicative.getSet();
    }

    @Override
    public Group<T> getMultiplicativeGroup() {
        return multiplicative;
    }

    /**
     * Checks if the additive set and the multiplicative set are compatible (same size and same elements)
     * @param additive
     * @param multiplicative
     */
    private void checkSet(Group<T> additive, Group<T> multiplicative){
        if(additive.getSet().size() != multiplicative.getSet().size()){
            throw new RuntimeException("The additive group must operate on the same set as the multiplicative group!");
        }

        for(int i=0;i<additive.getSet().size();i++){
            if(!additive.getSet().get(i).equals(multiplicative.getSet().get(i))){
                throw new RuntimeException("The additive group must operate on the same set as the multiplicative group! " + additive.getSet().get(i) + " - " + multiplicative.getSet().get(i));
            }
        }
    }
}
