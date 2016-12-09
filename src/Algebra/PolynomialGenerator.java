package Algebra;

/**
 * Created by mugeebhassan on 09/12/16.
 */
public class PolynomialGenerator<T> {

    private Field<T> baseField;
    private String variableSymbol;

    public PolynomialGenerator(Field<T> field) {
        this(field, "x");
    }

    public PolynomialGenerator(Field<T> field, String variableSymbol){
        this.baseField = field;
        this.variableSymbol = variableSymbol;
    }

    /**
     * generates a polynomial with the specified coefficients and returns it
     * @param coefficients
     * @return polynomial with the specified coefficients
     */
    public Polynomial<T> generatePolynomial(T... coefficients){
        for(int i=0;i<coefficients.length;i++){
            coefficients[i] = baseField.add(baseField.additiveIdentity(), coefficients[i]);
        }

        return new Polynomial<>(baseField,variableSymbol,coefficients);
    }

}
