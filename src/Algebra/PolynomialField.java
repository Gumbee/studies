package Algebra;

import java.util.ArrayList;

/**
 * Created by mugeebhassan on 08/12/16.
 */
public class PolynomialField<T> implements Field<Polynomial<T>> {

    Field<T> field;

    public PolynomialField(Field<T> field){
        this.field = field;
    }

    @Override
    public Polynomial<T> multiplicativeInverse(Polynomial<T> A) {
        return new Polynomial<T>(field, field.additiveIdentity());
    }

    @Override
    public Group<Polynomial<T>> getMultiplicativeGroup() {
        return null;
    }

    @Override
    public Polynomial<T> add(Polynomial<T> A, Polynomial<T> B) {
        return A.add(B);
    }

    @Override
    public Polynomial<T> mult(Polynomial<T> A, Polynomial<T> B) {
        return null;
    }

    @Override
    public Polynomial<T> additiveInverse(Polynomial<T> A) {
        return null;
    }

    @Override
    public Polynomial<T> additiveIdentity() {
        ArrayList<T> co = new ArrayList<>();
        co.add(field.additiveIdentity());

        return new Polynomial<T>(field, co);
    }

    @Override
    public Polynomial<T> multiplicativeIdentity() {
        return null;
    }

    @Override
    public Group<Polynomial<T>> getAdditiveGroup() {
        return null;
    }

    @Override
    public Monoid<Polynomial<T>> getMultiplicativeMonoid() {
        return null;
    }

    @Override
    public ArrayList<Polynomial<T>> getSet() {
        return null;
    }
}
