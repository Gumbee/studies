package Algebra;

import java.util.ArrayList;

/**
 * Created by mugeebhassan on 21/12/16.
 */
public class PolynomialSuggester<T> {

    private Field<T> field;

    public PolynomialSuggester(Field<T> field){
        this.field = field;
    }

    /**
     * returns a single irreducible polynomial of degree m
     */
    public Polynomial<T> getIrreduciblePolynomial(int m){
        PolynomialField<T> polynomialField = getPolynomialField(m);

        // TODO: improve prime-finder algorithm
        for(Polynomial<T> p:polynomialField.getSet()) {
            if(p.deg() != m+1){
                // only test polynomials with the desired degree
                continue;
            }

            boolean foundIrreducible = true;
            for(Polynomial<T> q:polynomialField.getSet()){
                if(q.deg() < p.deg() && q.deg() > 0 && polynomialField.gcdV(p,q).deg() > 1){
                    // if we find a polynomial of smaller degree that has a common divisor with degree
                    // bigger than 1, then p is not irreducible
                    foundIrreducible = false;
                    break;
                }
            }

            if(foundIrreducible){
                return p;
            }
        }

        return null;
    }

    /**
     * returns a list of irreducible polynomials of degree m
     */
    public ArrayList<Polynomial<T>> getIrreduciblePolynomials(int m){
        PolynomialField<T> polynomialField = getPolynomialField(m);

        // output array
        ArrayList<Polynomial<T>> irreducible = new ArrayList<>();

        // TODO: improve prime-finder algorithm
        for(Polynomial<T> p:polynomialField.getSet()) {
            if(p.deg() != m+1){
                // only test polynomials with the desired degree
                continue;
            }

            boolean foundIrreducible = true;
            for(Polynomial<T> q:polynomialField.getSet()){
                if(q.deg() < p.deg() && q.deg() > 0 && polynomialField.gcdV(p,q).deg() > 1){
                    // if we find a polynomial of smaller degree that has a common divisor with degree
                    // bigger than 1, then p is not irreducible
                    foundIrreducible = false;
                    break;
                }
            }

            if(foundIrreducible){
                irreducible.add(p);
            }
        }

        return irreducible;
    }

    private void orderTester(PolynomialField<T> polynomialField){
        for(Polynomial<T> p:polynomialField.getSet()) {
            if(p.equals(polynomialField.additiveIdentity()) || p.deg() < 2){
                continue;
            }

            Polynomial<T> o = p;
            Polynomial<T> t = o;

            int order = 1;
            System.out.println("Polynomial is: " + p.toString());

            while (!t.equals(polynomialField.multiplicativeIdentity())) {
                System.out.println(t.toString());
                t = polynomialField.mult(t, o);
                order++;
            }


            System.out.println("Order is: " + order);
            if(order == polynomialField.getSet().size()-1){
                break;
            }
        }
    }

    private PolynomialField<T> getPolynomialField(int m){
        ArrayList<T> coefficients = new ArrayList<>();
        // fill first m coefficients with zero elements
        for(int j=0;j<m+1;j++){
            coefficients.add(field.additiveIdentity());
        }
        // add a "one" element after m coefficients so that we have an extension field with more
        // set elements of degree smaller or equal to m
        coefficients.add(field.multiplicativeIdentity());
        // the polynomial field is modulo this modulus
        Polynomial<T> modulus = new Polynomial<T>(field, "f", coefficients);

        PolynomialField<T> polynomialField = new PolynomialField<>(field, modulus);

        return polynomialField;
    }
}