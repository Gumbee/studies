package Algebra;

import java.util.ArrayList;

/**
 * Created by mugeebhassan on 04/12/16.
 */
public class ZModGroup extends ZModMonoid implements Group<Integer> {

    public ZModGroup(int mod, ZModType type){
        super(mod, type);

        set = new ArrayList<>();

        // in a multiplicative group modulo m, the elements of the set must be coprime to m so that an inverse element
        // can be calculated (inverse elements are a requirement for groups!)
        for(int i=0;i<mod;i++){
            if(type == ZModType.additive){
                set.add(i);
            }else if(type == ZModType.multiplicative && gcd(mod, i).gcd == 1 || i==0){
                set.add(i);
            }
        }
    }

    @Override
    public Integer inverse(Integer A) {
        A = getElement(A);

        // since we defined two different operations for this group(add and multiply) we have to specify
        // which operation this instance is using and then output the value accordingly
        switch (type){
            case additive:
                return additiveInverse(A);
            case multiplicative:
                return multiplicativeInverse(A);
            default:
                throw new RuntimeException("No type found in ZModGroup.");
        }
    }

    /*==========================================
     * Custom Methods
     ===========================================*/

    /**
     * returns the additive inverse of an element
     */
    private final Integer additiveInverse(Integer A){
        return (mod)-A;
    }

    /**
     * returns the multiplicative inverse of an element
     */
    private final Integer multiplicativeInverse(Integer A){
        return Math.floorMod(gcd(mod, A).v, mod);
    }


    /*==========================================
     * Extended Euclidian Algorithm
     ===========================================*/

    private class GCDTupel {
        public int u;
        public int v;
        public int gcd;

        public GCDTupel(int u, int v, int gcd){
            this.u = u;
            this.v = v;
            this.gcd = gcd;
        }
    }

    /**
     * uses the extended euclidean algorithm to find u and v such that u*a + v*b = gcd(a,b)
     */
    private GCDTupel gcd(int a, int b){
        if(b>a){
            int tmp = b;
            b = a;
            a = tmp;
        }

        int s1 = a;
        int s2 = b;

        int u1 = 1;
        int u2 = 0;
        int v1 = 0;
        int v2 = 1;

        while (s2 > 0){
            int q = Math.floorDiv(s1, s2);
            // remainder
            int r = s1-q*s2;
            s1 = s2;
            s2 = r;

            int tmp = u2;
            u2 = u1 - q*u2;
            u1 = tmp;

            tmp = v2;
            v2 = v1 - q*v2;
            v1 = tmp;

        }

        return new GCDTupel(u1, v1, s1);
    }
}
