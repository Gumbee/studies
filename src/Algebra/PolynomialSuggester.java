package Algebra;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mugeebhassan on 21/12/16.
 */
public class PolynomialSuggester<T> {

    private Field<T> field;

    public PolynomialSuggester(Field<T> field){
        this.field = field;
    }

    /**
     * returns a single irreducible polynomial of degree m (very, very hardcoded at the moment. For now this only
     * has to work)
     */
    public Polynomial<T> getRandomIrreduciblePolynomial(int m){
        PolynomialField<T> polynomialField = getPolynomialField(m);

        boolean isIrreducible = false;
        Polynomial<T> identity = polynomialField.multiplicativeIdentity();

        while (!isIrreducible) {
            Polynomial<T> candidate = getRandomPolynomial(m);

            PolynomialField<T> potentialField = new PolynomialField<>(field, candidate);

            int cardinality = (int) Math.pow(field.getSet().size(), candidate.deg()-1);
            cardinality--;

            // System.out.println("Candidate: " + candidate.toString());

            boolean breakOut = false;

            for(int j=1;j<candidate.deg()-1;j++){
                for(int i=0;i<field.getSet().size();i++){
                    Polynomial<T> p = getRandomPolynomial(j);
                    Polynomial<T> tmp = p;

                    Polynomial<T> failsafe = potentialField.additiveIdentity();

                    int order = 1;
                    int failNumber = 2;
                    while (!tmp.equals(potentialField.additiveIdentity()) && !tmp.equals(potentialField.multiplicativeIdentity()) && !tmp.equals(failsafe) && order <= cardinality){
                        if(order == failNumber) {
                            failsafe = tmp;
                            failNumber *= failNumber;
                        }
                        tmp = potentialField.mult(tmp, p);
                        order++;
                    }

                    if(simpleGCD(cardinality, order) != order || order > cardinality || tmp.equals(failsafe)){
                        breakOut = true;
                        break;
                    }

                    if(order == cardinality){
                        return candidate;
                    }
                }
                if(breakOut){
                    break;
                }
            }

            if(!breakOut){
                isIrreducible = true;
            }

            if(isIrreducible){
                return candidate;
            }
        }

        return null;
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

            boolean isIrreducible = testIrreducibility(polynomialField, p);

            if(isIrreducible){
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

            boolean isIrreducible = testIrreducibility(polynomialField, p);

            if(isIrreducible){
                irreducible.add(p);
            }
        }

        return irreducible;
    }

    /**
     * prints the order of each element of the field to the console
     * @param polynomialField
     */
    public void getGenerator(PolynomialField<T> polynomialField){
        for(Polynomial<T> p:polynomialField.getSet()) {
            if(p.equals(polynomialField.additiveIdentity()) || p.deg() < 2){
                continue;
            }

            Polynomial<T> o = p;
            Polynomial<T> t = o;

            int order = 1;

            while (!t.equals(polynomialField.multiplicativeIdentity())) {
                t = polynomialField.mult(t, o);
                order++;
            }


            if(order == polynomialField.getSet().size()-1){
                System.out.println("Generator is: " + p.toString());
                break;
            }
        }
    }

    /**
     * creates the polynomial field that contains the polynomials of the desired degree
     * @param m desired degree
     * @return polynomial field containing those elements
     */
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

    /**
     * checks if a polynomial is irreducible over a certain field
     * @param polynomialField
     * @param candidate
     * @return
     */
    private boolean testIrreducibility(PolynomialField<T> polynomialField, Polynomial<T> candidate){
        for(Polynomial<T> q:polynomialField.getSet()){
            if(q.deg() < candidate.deg() && q.deg() > 0 && polynomialField.gcdV(candidate,q).deg() > 1){
                // if we find a polynomial of smaller degree that has a common divisor with degree
                // bigger than 1, then p is not irreducible
                return false;
            }
        }

        return true;
    }

    /**
     * a simple gcd calculator for integers
     * @return gcd of a and b
     */
    public int simpleGCD(int a, int b){
        if(b > a){
            int tmp = b;
            b = a;
            a = tmp;
        }
        while (b != 0){
            int tmp = b;
            b = a%b;
            a = tmp;
        }

        return a;
    }

    /**
     * returns a random polynomial of a certain degree
     */
    public Polynomial<T> getRandomPolynomial(int m) {
        ArrayList<T> coefficients = new ArrayList<>();
        // fill first m coefficients with zero elements
        for(int j=0;j<m;j++){
            int random = new Random().nextInt(field.getSet().size());
            coefficients.add(field.getSet().get(random));
        }
        coefficients.add(field.getSet().get(new Random().nextInt(field.getSet().size())));

        if(coefficients.get(m).equals(field.additiveIdentity())){
            coefficients.set(m, field.multiplicativeIdentity());
        }

        return new Polynomial<>(field, "f", coefficients);
    }
}