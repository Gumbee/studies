package Util;

/**
 * Created by mugeebhassan on 04/02/17.
 */
public class SetNode<T> {

    public T item;
    // this set's parent
    public SetNode<T> parent;
    // this item's rank (determines who becomes the parent when merging sets)
    public int rank;

    public SetNode(T item){
        this.item = item;
        this.parent = this;
        this.rank = 0;
    }

    /**
     * finds out to which set this item belongs to and returns it
     * @return parent of the set to which this item belongs to
     */
    public T findSet(){
        SetNode<T> current = this;
        SetNode<T> parent = this.parent;

        while (!parent.equals(current)){
            current = parent;
            parent = current.parent;
        }

        // update the reference
        this.parent = parent;

        return parent.item;
    }

}