package Algebra;

/**
 * Created by mugeebhassan on 06/12/16.
 */
public class PolynomialField<T> extends Field<T> {

    private int degree = 0;
    // field over which we define the polynomial
    private Field<T> field;

    private T[] polynome;

    public PolynomialField(Field<T> field, T... coefficients){
        super(field.getAdditiveGroup(), field.getMultiplicativeGroup());

        int i=0;

        while (i< coefficients.length && coefficients[i] == field.additiveIdentity()){
            i++;
        }

        System.out.println("degree: " + (coefficients.length-i));
    }

    /**
     * performs an addition and returns the value
     */
    @Override
    public final T add(T A, T B){
        return additive.add(A, B);
    }

    /**
     * performs a "multiplication" and returns the value
     */
    @Override
    public final T mult(T A, T B){
        return multiplicative.add(A, B);
    }


}
