package Algebra;

import java.util.ArrayList;

/**
 * Created by mugeebhassan on 08/12/16.
 */
public class PolynomialField<T> implements Field<Polynomial<T>> {

    private Field<T> field;
    // save additive and multiplicative identity in variables so we don't have to create them frequently
    private Polynomial<T> additiveIdentity;
    private Polynomial<T> multiplicativeIdentity;
    private Polynomial<T> modulus;

    private ArrayList<Polynomial<T>> set;

    public PolynomialField(Field<T> field, Polynomial<T> modulus){
        this.field = field;
        this.additiveIdentity = new Polynomial<>(field, "y", field.additiveIdentity());
        this.multiplicativeIdentity = new Polynomial<>(field, "y", field.multiplicativeIdentity());
        this.modulus = modulus;
        this.set = new ArrayList<>();
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
        // if the set is not generated yet, generate it
        if(set.size() == 0){
            makeSet();
        }

        return set;
    }

    /**
     * set generation is handled lazily to provide better performance
     */
    private void makeSet(){
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

    /*==========================================
     * Extended Euclidean Algorithm
     ===========================================*/

    private class GCDTupel {
        Polynomial<T> u;
        Polynomial<T> v;
        Polynomial<T> gcd;

        public GCDTupel(Polynomial<T> u, Polynomial<T> v, Polynomial<T> gcd){
            this.u = u;
            this.v = v;
            this.gcd = gcd;
        }
    }

    /**
     * uses the extended euclidean algorithm to find u and v such that u*a + v*b = gcd(a,b)
     */
    public GCDTupel gcd(Polynomial<T> a, Polynomial<T> b){
        if(b.deg() > a.deg()){
            Polynomial<T> tmp = b;
            b = a;
            a = tmp;
        }

        Polynomial<T> s1 = a;
        Polynomial<T> s2 = b;

        // will satisfy the equation u*a-v*b = gcd(a,b)
        Polynomial<T> u1 = multiplicativeIdentity;
        Polynomial<T> u2 = additiveIdentity;
        Polynomial<T> v1 = additiveIdentity;
        Polynomial<T> v2 = multiplicativeIdentity;

        while (s2.deg() > 0){
            Polynomial<T> q = s1.div(s2);
            // remainder
            Polynomial<T> r = s1.divR(s2);
            s1 = s2;
            s2 = r;

            Polynomial<T> tmp = u2;
            u2 = sub(u1, mult(q,u2));
            u1 = tmp;

            tmp = v2;
            v2 = sub(v1,mult(q,v2));
            v1 = tmp;

        }

        // u*a-v*b = gcd(a,b)
        // sub(mult(u1,a), mult(v1, b)).print();

        return new GCDTupel(u1, v1, s1);
    }

    /**
     * uses the extended euclidean algorithm to find u and v such that u*a + v*b = gcd(a,b)
     */
    public Polynomial<T> gcdV(Polynomial<T> a, Polynomial<T> b){
        if(b.deg() > a.deg()){
            Polynomial<T> tmp = b;
            b = a;
            a = tmp;
        }

        Polynomial<T> s1 = a;
        Polynomial<T> s2 = b;

        // will satisfy the equation u*a-v*b = gcd(a,b)
        Polynomial<T> u1 = multiplicativeIdentity;
        Polynomial<T> u2 = additiveIdentity;
        Polynomial<T> v1 = additiveIdentity;
        Polynomial<T> v2 = multiplicativeIdentity;

        while (s2.deg() > 0){
            Polynomial<T> q = s1.div(s2);
            // remainder
            Polynomial<T> r = s1.divR(s2);
            s1 = s2;
            s2 = r;

            Polynomial<T> tmp = u2;
            u2 = u1.sub(q.mult(u2));
            u1 = tmp;

            tmp = v2;
            v2 = v1.sub(q.mult(v2));
            v1 = tmp;

        }

        return s1;
    }

}
