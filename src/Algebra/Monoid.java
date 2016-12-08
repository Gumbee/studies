package Algebra;

import java.util.ArrayList;

/**
 * Created by mugeebhassan on 04/12/16.
 */
public interface Monoid<T> {

    // returns the set of elements that make up this monoid
    ArrayList<T> getSet();

    // returns the value of the binary operation T x T -> T that satisfies the following rule: (a • b) • c = a • (b • c)
    T add(T A, T B);

    // returns the identity element e sucht that for every a following holds: (a • e) = (e • a) = a
    T identity();

}
