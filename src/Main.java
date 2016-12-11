import Algebra.*;
import Graphs.Graph;
import Graphs.Vertex;

import java.util.ArrayList;

/**
 * Created by mugeebhassan on 25/11/16.
 */
public class Main {

    public static void main(String[] args){

        ZModGroup modAdd = new ZModGroup(7, ZModType.additive);
        ZModGroup modMult = new ZModGroup(7, ZModType.multiplicative);

        GaloisField<Integer> GF = new GaloisField<>(modAdd, modMult);

        PolynomialField<Integer> PolynomialField = new PolynomialField<>(GF);
        PolynomialField<Polynomial<Integer>> PolyPolynomialField = new PolynomialField<>(PolynomialField);

        Polynomial<Integer> a = new Polynomial<>(GF,2,3,1,1,5);
        Polynomial<Integer> b = new Polynomial<>(GF,1,1,2,2);
        Polynomial<Integer> c = new Polynomial<>(GF,1,2);
        Polynomial<Integer> d = new Polynomial<>(GF,3,2,1,2);
        Polynomial<Integer> e = new Polynomial<>(GF,1,3,2,1);

        Polynomial<Polynomial<Integer>> f = new Polynomial<>(PolynomialField,"y", a,b,c);
        Polynomial<Polynomial<Integer>> g = new Polynomial<>(PolynomialField,"y", d,e,b);
        Polynomial<Polynomial<Integer>> h = new Polynomial<>(PolynomialField,"y", e,a);
        Polynomial<Polynomial<Integer>> i = new Polynomial<>(PolynomialField,"y", d,d);

        Polynomial<Polynomial<Polynomial<Integer>>> j = new Polynomial<>(PolyPolynomialField,"z", f,g);
        Polynomial<Polynomial<Polynomial<Integer>>> k = new Polynomial<>(PolyPolynomialField,"z", h,i);

        a.print();
        b.print();

        j.add(k).print();

    }
}
