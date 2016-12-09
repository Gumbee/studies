package Algebra;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by mugeebhassan on 08/12/16.
 */
public class Polynomial<T> {

    // coefficients of the polynomial
    public ArrayList<T> coefficients;
    // field over which the polynomial is defined
    public Field<T> field;
    // string representation of the polynomials variable (e.g "x")
    private String variable;


    public Polynomial(Field<T> field, String variable, T... coefficients){
        ArrayList<T> tmp = new ArrayList<>();

        for(T c:coefficients){
            tmp.add(c);
        }

        initializePolynomial(field, variable, tmp);
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
                stack.add(coefficients.get(i));
            }
        }

        for(T coefficient:stack) {
            this.coefficients.add(coefficient);
        }

    }

    /*==========================================
     * Polynomial Operations
     ===========================================*/

    /**
     * adds two polynomials and returns the sum
     */
    public Polynomial<T> add(Polynomial<T> B) {

        ArrayList<T> sums = new ArrayList<>();

        for(int i=0;i<Math.max(coefficients.size(), B.coefficients.size());i++){
            int sizeA = coefficients.size();
            int sizeB = B.coefficients.size();

            // depending on which array is bigger, copy array A to the new polynomial and then add B or copy B and then
            // add A (because degree of A may be bigger than degree of B or vice versa)
            sums.add(i, (sizeA>=sizeB?coefficients.get(i):B.coefficients.get(i)));

            if (i < (sizeA>=sizeB?B.coefficients.size():coefficients.size())) {
                T value = field.add(sums.get(i), (sizeA>=sizeB?B.coefficients.get(i):coefficients.get(i)));

                sums.set(i, value);
            }
        }

        return new Polynomial<>(field, B.variable, sums);
    }

    /*==========================================
     * Polynomial Representation
     ===========================================*/

    /**
     * print the polynomial to the console
     */
    public void print(){
        System.out.println();
        System.out.println(polynomialString());
    }

    /**
     * converts the polynomial to a string
     * @return
     */
    private String polynomialString(){
        if(coefficients.size() == 0){
            return "";
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
        output += polynomialString();
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
