package Algebra;

/**
 * Created by mugeebhassan on 04/12/16.
 */
public interface Group<T> extends Monoid<T> {

    // returns the inverse of element A
    T inverse(T A);

}
