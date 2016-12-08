import Algebra.*;

import java.util.ArrayList;

/**
 * Created by mugeebhassan on 25/11/16.
 */
public class Main {

    public static void main(String[] args){

        ZModGroup modAdd = new ZModGroup(2, ZModType.additive);
        ZModGroup modMult = new ZModGroup(2, ZModType.multiplicative);

        GaloisField<Integer> GF = new GaloisField<>(modAdd, modMult);
        PolynomialField<Integer> PolynomialField = new PolynomialField<>(GF);
        PolynomialField<Polynomial<Integer>> PolyPolynomialField = new PolynomialField<>(PolynomialField);

        Polynomial<Integer> polynomialA = new Polynomial<>(GF, 1, 0, 1, 1);
        Polynomial<Integer> polynomialB = new Polynomial<>(GF, 1, 1, 0, 0);

        Polynomial<Integer> polynomialC = new Polynomial<>(GF, 1, 0, 1, 0, 1);
        Polynomial<Integer> polynomialD = new Polynomial<>(GF, 1, 0, 0, 0);

        ArrayList<Polynomial<Integer>> coefficientsA = new ArrayList<>();

        coefficientsA.add(polynomialA);
        coefficientsA.add(polynomialB);

        ArrayList<Polynomial<Integer>> coefficientsB = new ArrayList<>();

        coefficientsB.add(polynomialC);
        coefficientsB.add(polynomialD);

        Polynomial<Polynomial<Integer>> polyPolynomialA = new Polynomial<>(PolynomialField, coefficientsA);
        Polynomial<Polynomial<Integer>> polyPolynomialB = new Polynomial<>(PolynomialField, coefficientsB);

        PolyPolynomialField.add(polyPolynomialA, polyPolynomialB).print();


    }
}
