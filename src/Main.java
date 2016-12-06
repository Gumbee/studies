import Algebra.*;

/**
 * Created by mugeebhassan on 25/11/16.
 */
public class Main {

    public static void main(String[] args){

        ZModGroup modAdd = new ZModGroup(17, ZModType.additive);
        ZModGroup modMult = new ZModGroup(17, ZModType.multiplicative);

        for(int e: modMult.set()){
            System.out.println(e);
        }

        Integer a = 2;
        Integer b = 5;
        Integer c = 7;

        System.out.println();
        System.out.println();

        Field<Integer> field = new Field<>(modAdd, modMult);

        System.out.println(field.mult(a, field.add(b,c)));

        System.out.println(field.multiplicativeInverse(c));

        PolynomialField<Integer> polynomialField = new PolynomialField<>(field);


    }

}
