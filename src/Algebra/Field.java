package Algebra;

/**
 * Created by mugeebhassan on 06/12/16.
 */
public class Field<T> extends Ring<T>{

    private Group<T> multiplicativeGroup;

    public Field(Group<T> additive, Group<T> multiplicative){
        super(additive, multiplicative);
        this.multiplicativeGroup = multiplicative;
    }

    /**
     * returns the additive inverse
     */
    public T multiplicativeInverse(T A){
        return multiplicativeGroup.inverse(A);
    }

    public Group<T> getAdditiveGroup(){
        return additive;
    }

    public Group<T> getMultiplicativeGroup() {
        return multiplicativeGroup;
    }
}
