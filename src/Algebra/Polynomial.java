package Algebra;

import java.util.ArrayList;

/**
 * Created by mugeebhassan on 08/12/16.
 */
public class Polynomial<T> {

    public ArrayList<T> coefficients;
    public Field<T> field;

    public Polynomial(Field<T> field, T... coefficients){
        this.coefficients = new ArrayList<>();
        this.field = field;

        for(T c:coefficients){
            this.coefficients.add(c);
        }
    }

    public Polynomial(Field<T> field, ArrayList<T> coefficients){
        this.coefficients = new ArrayList<>();
        this.field = field;

        for(T c:coefficients){
            this.coefficients.add(c);
        }
    }

    public Polynomial<T> add(Polynomial<T> B) {

        ArrayList<T> sums = new ArrayList<>();

        for(int i=0;i<Math.max(coefficients.size(), B.coefficients.size());i++){
            if(i>=sums.size()){
                sums.add(field.additiveIdentity());
            }

            if(i<coefficients.size()){
                T value = field.add(sums.get(i), coefficients.get(i));

                sums.set(i, value);
            }

            if(i<B.coefficients.size()){
                T value = field.add(sums.get(i), B.coefficients.get(i));

                sums.set(i, value);
            }
        }


        for(int i=sums.size()-1;i>=0;i--){
            if(sums.get(i) == field.additiveIdentity()){
                sums.remove(i);
            }else{
                break;
            }
        }

        Polynomial<T> sum = new Polynomial<>(field, sums);

        return sum;
    }

    public void print(){
        System.out.println();
        for(int i=0;i<coefficients.size();i++){
            System.out.print(coefficients.get(i) + (i>0?"x" +(i>1?"^"+ i:""):"") + (i<coefficients.size()-1?" + ":""));
        }
    }

    @Override
    public String toString() {
        String output = "[";
        for(int i=0;i<coefficients.size();i++) {
            if (coefficients.get(i) != field.additiveIdentity()) {
                output += ((coefficients.get(i)!=field.multiplicativeIdentity()?coefficients.get(i):"") + (i > 0 ? (field instanceof PolynomialField?"x":"y") + (i > 1 ? "^" + i : "") : "") + (i < coefficients.size() - 1 ? " + " : ""));
            }
        }
        output += "]";
        output = superscript(output);
        return output;
    }

    public static String superscript(String str) {
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

}
