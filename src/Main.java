import Algebra.Ring;
import Algebra.ZModGroup;
import Algebra.ZModMonoid;
import Algebra.ZModType;

/**
 * Created by mugeebhassan on 25/11/16.
 */
public class Main {

    public static void main(String[] args){

        ZModGroup modAdd = new ZModGroup(19, ZModType.additive);
        ZModMonoid modMult = new ZModMonoid(19, ZModType.multiplicative);

        for(int e: modMult.set()){
            System.out.println(e);
        }

        Integer a = 2;
        Integer b = 5;
        Integer c = 7;

        System.out.println();
        System.out.println();

        Ring<Integer> ring = new Ring<Integer>(modAdd, modMult);

        System.out.println(ring.mult(a, ring.add(b,c)));



    }

}
