package Algebra;

import java.util.ArrayList;

/**
 * Created by mugeebhassan on 08/12/16.
 */
public class PolynomialField<T> implements Field<Polynomial<T>> {

    Field<T> field;
    // save additive and multiplicative identity in variables so we don't have to create them frequently
    Polynomial<T> additiveIdentity;
    Polynomial<T> multiplicativeIdentity;

    public PolynomialField(Field<T> field){
        this.field = field;
        this.additiveIdentity = new Polynomial<>(field, "y", field.additiveIdentity());
        this.multiplicativeIdentity = new Polynomial<>(field, "y", field.multiplicativeIdentity());
    }

    @Override
    public Polynomial<T> multiplicativeInverse(Polynomial<T> A) {
        return null;
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
    public Polynomial<T> sub(Polynomial<T> A, Polynomial<T> B) {
        return B.sub(A);
    }

    @Override
    public Polynomial<T> mult(Polynomial<T> A, Polynomial<T> B) {
        return A.mult(B);
    }

    @Override
    public Polynomial<T> additiveInverse(Polynomial<T> A) {
        return null;
    }

    @Override
    public Polynomial<T> additiveIdentity() {
        return this.additiveIdentity;
    }

    @Override
    public Polynomial<T> multiplicativeIdentity() {
        return this.multiplicativeIdentity;
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
