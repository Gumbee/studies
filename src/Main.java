import Algebra.*;
import Graphs.Graph;
import Graphs.Vertex;

import java.util.ArrayList;

/**
 * Created by mugeebhassan on 25/11/16.
 */
public class Main {

    public static void main(String[] args){

        ZModGroup modAdd = new ZModGroup(5, ZModType.additive);
        ZModGroup modMult = new ZModGroup(5, ZModType.multiplicative);

        GaloisField<Integer> GF = new GaloisField<>(modAdd, modMult);
        PolynomialField<Integer> PolynomialField = new PolynomialField<>(GF);
        PolynomialField<Polynomial<Integer>> PolyPolynomialField = new PolynomialField<>(PolynomialField);

        PolynomialGenerator<Integer> generator = new PolynomialGenerator<>(GF, "z");
        PolynomialGenerator<Polynomial<Integer>> generatorPoly = new PolynomialGenerator<>(PolynomialField, "y");
        PolynomialGenerator<Polynomial<Polynomial<Integer>>> generatorPolyPoly = new PolynomialGenerator<>(PolyPolynomialField);

        Polynomial<Integer> a = generator.generatePolynomial(2,3,1,1,5);
        Polynomial<Integer> b = generator.generatePolynomial(1,3,2,2);
        Polynomial<Integer> c = generator.generatePolynomial(1,2);
        Polynomial<Integer> d = generator.generatePolynomial(3,2,1,2);
        Polynomial<Integer> e = generator.generatePolynomial(1,3,2,1);

        Polynomial<Polynomial<Integer>> f = generatorPoly.generatePolynomial(a,b,c);
        Polynomial<Polynomial<Integer>> g = generatorPoly.generatePolynomial(d,e,b);
        Polynomial<Polynomial<Integer>> h = generatorPoly.generatePolynomial(e,a);
        Polynomial<Polynomial<Integer>> i = generatorPoly.generatePolynomial(d,d);

        Polynomial<Polynomial<Polynomial<Integer>>> j = generatorPolyPoly.generatePolynomial(f,g);
        Polynomial<Polynomial<Polynomial<Integer>>> k = generatorPolyPoly.generatePolynomial(h,i);

        j.print();
        k.print();

        j.add(k).print();

        a.add(e).print();
    }
}
