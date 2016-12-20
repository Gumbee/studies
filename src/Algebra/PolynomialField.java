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
    Polynomial<T> modulus;

    ArrayList<Polynomial<T>> set;

    public PolynomialField(Field<T> field, Polynomial<T> modulus){
        this.field = field;
        this.additiveIdentity = new Polynomial<>(field, "y", field.additiveIdentity());
        this.multiplicativeIdentity = new Polynomial<>(field, "y", field.multiplicativeIdentity());
        this.modulus = modulus;
        this.set = new ArrayList<>();

        // beginning of set generation
        for(T element:field.getSet()){
            set.add(new Polynomial<>(field, "f", element));
        }

        for(int i=1;i<modulus.deg()-1;i++){
            int tmpSize = set.size();

            for(T element:field.getSet()){
                ArrayList<T> coefficients = new ArrayList<>();
                for(int j=0;j<i;j++){
                    coefficients.add(field.additiveIdentity());
                }
                coefficients.add(element);
                Polynomial<T> x = new Polynomial<T>(field, "f", coefficients);

                for(int k=0;k<tmpSize;k++){
                    if(x.add(set.get(k)).deg() == i+1) {
                        set.add(x.add(set.get(k)));
                    }
                }
            }
        }
        // end of set generation

        System.out.println("Size is " + set.size() + " and that is equal to " + (Math.pow(field.getSet().size(), modulus.deg()-1)) + "?");
    }

    @Override
    public Polynomial<T> multiplicativeInverse(Polynomial<T> A) {
        // TODO: convert to a correct answer
        ArrayList<T> coefficients = new ArrayList<>(A.getCoefficients());
        for(int i=0;i<coefficients.size();i++){
            coefficients.set(i, field.multiplicativeInverse(coefficients.get(i)));
        }
        return new Polynomial<>(field, A.variable, coefficients);
    }

    @Override
    public Group<Polynomial<T>> getMultiplicativeGroup() {
        return null;
    }

    @Override
    public Polynomial<T> add(Polynomial<T> A, Polynomial<T> B) {
        return A.add(B).mod(modulus);
    }

    @Override
    public Polynomial<T> sub(Polynomial<T> A, Polynomial<T> B) {
        return B.sub(A).mod(modulus);
    }

    @Override
    public Polynomial<T> mult(Polynomial<T> A, Polynomial<T> B) {
        return A.mult(B).mod(modulus);
    }

    @Override
    public Polynomial<T> div(Polynomial<T> A, Polynomial<T> B) {
        return A.div(B).mod(modulus);
    }

    @Override
    public Polynomial<T> additiveInverse(Polynomial<T> A) {
        ArrayList<T> coefficients = new ArrayList<>(A.getCoefficients());
        for(int i=0;i<coefficients.size();i++){
            coefficients.set(i, field.additiveInverse(coefficients.get(i)));
        }
        return new Polynomial<T>(field, A.variable, coefficients);
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
        return set;
    }

}
