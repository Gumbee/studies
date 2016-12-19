package Algebra;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by mugeebhassan on 08/12/16.
 */
public class Polynomial<T> {

    // coefficients of the polynomial
    private ArrayList<T> coefficients;
    // field over which the polynomial is defined
    public Field<T> field;
    // string representation of the polynomials variable (e.g "x")
    public String variable;

    @SafeVarargs
    public Polynomial(Field<T> field, T... coefficients){
        this(field, "x", coefficients);
    }

    @SafeVarargs
    public Polynomial(Field<T> field, String variable, T... coefficients){
        ArrayList<T> tmp = new ArrayList<>();

        for(int i=0;i<coefficients.length;i++){
            tmp.add(coefficients[i]);
        }

        initializePolynomial(field, variable, tmp);
    }

    public Polynomial(Field<T> field, ArrayList<T> coefficients){
        this(field, "x", coefficients);
    }

    public Polynomial(Field<T> field, String variable, ArrayList<T> coefficients){
        initializePolynomial(field, variable, coefficients);
    }

    /**
     * initializes the polynomial. It's a separate method so that when changes are made, all changes are applied to
     * all different constructors simultaneously
     * @param field
     * @param variable
     * @param coefficients
     */
    private void initializePolynomial(Field<T> field, String variable, ArrayList<T> coefficients){
        this.field = field;
        this.variable = variable;
        this.coefficients = new ArrayList<>();

        Stack<T> stack = new Stack<>();

        // if set to true, we can no longer delete zero elements since they are not leading zeros
        boolean leadingZeros = true;

        for(int i=coefficients.size()-1;i>=0;i--) {
            // if the current coefficient is not a neutral element (e.g. zero) then we can ignore it if it is
            // a leading coefficient (e.g 2x¹ + 0x² + x³ + 0x⁴ + 0x⁵ = 2x¹ + 0x² + x³)
            if(!coefficients.get(i).equals(field.additiveIdentity()) || !leadingZeros){
                leadingZeros = false;
                stack.push(coefficients.get(i));
            }
        }

        while (!stack.empty()) {
            this.coefficients.add(field.add(field.additiveIdentity(), stack.pop()));
        }

    }

    /*==========================================
     * Polynomial Operations
     ===========================================*/

    /**
     * adds two polynomials and returns the sum
     */
    public Polynomial<T> add(Polynomial<T> B) {
        // check if B is defined over the same field as this polynomial
        checkField(B);

        return new Polynomial<>(field, B.variable, coefficientsAdd(coefficients, B.coefficients));
    }

    /**
     * subtracts the polynomial B from this polynomial
     */
    public Polynomial<T> sub(Polynomial<T> B) {
        // check if B is defined over the same field as this polynomial
        checkField(B);

        return new Polynomial<>(field, B.variable, coefficientsSub(B.coefficients, coefficients));
    }

    /**
     * multiplies a polynomial with this polynomial and returns the result (naive algorithm)
     */
    public Polynomial<T> mult(Polynomial<T> B){
        // check if B is defined over the same field as this polynomial
        checkField(B);

        ArrayList<T> a = new ArrayList<>(coefficients);
        ArrayList<T> b = new ArrayList<>(B.coefficients);

        ArrayList<T> result = new ArrayList<>();

        for(int i=0;i<a.size()+b.size()-1;i++) {
            result.add(field.additiveIdentity());
        }

        for(int i=0;i<a.size();i++){
            for(int j=0;j<b.size();j++) {
                T value = field.add(result.get(i+j), field.mult(a.get(i), b.get(j)));

                result.set(i+j, value);
            }
        }

        return new Polynomial<>(field, variable, result);
    }


    /**
     * divides this polynomial with the polynomial B and returns the result (without the remainder). division is
     * euclidean division
     * @return the quotient
     */
    public Polynomial<T> div(Polynomial<T> B){
        // check if M is defined over the same field as this polynomial
        checkField(B);

        Polynomial<T> quotient = new Polynomial<>(field, variable, field.additiveIdentity());
        Polynomial<T> remainder = this;
        T leadingCoefficientB = B.getCoefficients().get(B.getCoefficients().size()-1);

        int degreeB  = B.deg();

        while (remainder.deg() >= degreeB){
            T leading = field.div(remainder.getCoefficients().get(remainder.coefficients.size()-1), leadingCoefficientB);

            ArrayList<T> newCoefficients = new ArrayList<>();

            for(int i=0;i<remainder.deg()-degreeB;i++){
                newCoefficients.add(field.additiveIdentity());
            }
            newCoefficients.add(leading);

            Polynomial<T> leadingPolynomial = new Polynomial<>(field, variable, newCoefficients);

            quotient = quotient.add(leadingPolynomial);
            remainder =  remainder.sub(leadingPolynomial.mult(B));
        }

        System.out.println("RESULT:\n" + toString() + " : " + B.toString() + " = " + quotient.toString() + " with remainder: " + remainder.toString());
        System.out.print("TEST:" + (quotient.mult(B).add(remainder).equals(this)?" passed ✓":" failed ✗"));
        quotient.mult(B).add(remainder).print();

        return null;
    }



    /**
     * adds two ArrayLists as if they were coefficients of a polynomial
     */
    private ArrayList<T> coefficientsAdd(ArrayList<T> a, ArrayList<T> b){
        ArrayList<T> sums = new ArrayList<>();

        for(int i=0;i<Math.max(a.size(), b.size());i++){
            int sizeA = a.size();
            int sizeB = b.size();

            // depending on which array is bigger, copy array A to the new polynomial and then add B or copy B and then
            // add A (because degree of A may be bigger than degree of B or vice versa)
            sums.add(i, (sizeA>=sizeB?a.get(i):b.get(i)));

            if (i < (sizeA>=sizeB?b.size():a.size())) {
                T value = field.add(sums.get(i), (sizeA>=sizeB?b.get(i):a.get(i)));

                sums.set(i, value);
            }
        }

        return sums;
    }

    /**
     * subtracts two ArrayLists as if they were coefficients of a polynomial (subtract a from b)
     */
    private ArrayList<T> coefficientsSub(ArrayList<T> a, ArrayList<T> b){
        ArrayList<T> sums = new ArrayList<>();

        for(int i=0;i<Math.max(a.size(), b.size());i++){
            int sizeA = a.size();
            int sizeB = b.size();

            sums.add(i, (i>=sizeB?field.additiveIdentity():b.get(i)));

            T value = field.sub((i>=sizeA?field.additiveIdentity():a.get(i)), sums.get(i));

            sums.set(i, value);
        }

        return sums;
    }

    /*==========================================
     * Getter methods
     ===========================================*/

    public ArrayList<T> getCoefficients() {
        return coefficients;
    }

    public int deg(){
        return coefficients.size();
    }

    /*==========================================
     * Private methods
     ===========================================*/

    /**
     * throws an exception if the polynomial B's field is a different field than this polynomial's field.
     * @param B other polynomial whose field is compared to this polynomial's field
     */
    private void checkField(Polynomial<T> B){
        if(!field.equals(B.field)){
            throw new RuntimeException("Can't add two polynomials which are defined over different fields!");
        }
    }

    /*==========================================
     * Polynomial Representation
     ===========================================*/

    /**
     * print the polynomial to the console
     */
    public void print(){
        System.out.println();
        System.out.println(polynomialToString());
    }

    /**
     * converts the polynomial to a string
     * @return
     */
    private String polynomialToString(){
        if(coefficients.size() == 0){
            return field.additiveIdentity().toString();
        }

        if(coefficients.size() == 1 && coefficients.get(0) == field.multiplicativeIdentity()){
            return coefficients.get(0).toString();
        }

        String output = "";
        for(int i=0;i<coefficients.size();i++) {
            if (coefficients.get(i) != field.additiveIdentity() && coefficients.get(i).toString().length()>0) {
                output += ((!coefficients.get(i).equals(field.multiplicativeIdentity())||i==0?coefficients.get(i).toString():"") + (i > 0 ? variable + (i > 1 ? "^" + i : "") : "") + (i < coefficients.size() - 1 ? " + " : ""));
            }
        }

        return superscript(output);
    }

    private static String superscript(String str) {
        str = str.replace("^0", "⁰");
        str = str.replace("^1", "¹");
        str = str.replace("^2", "²");
        str = str.replace("^3", "³");
        str = str.replace("^4", "⁴");
        str = str.replace("^5", "⁵");
        str = str.replace("^6", "⁶");
        str = str.replace("^7", "⁷");
        str = str.replace("^8", "⁸");
        str = str.replace("^9", "⁹");
        return str;
    }


    @Override
    public String toString() {
        String output = "[";
        output += polynomialToString();
        output += "]";
        return output;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Polynomial.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        if (obj instanceof Polynomial<?>){
            if ( ((Polynomial<?>)obj).coefficients.equals(coefficients) ){
                return true;
            }
        }

        return false;
    }

}
