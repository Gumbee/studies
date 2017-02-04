package Util;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by mugeebhassan on 04/02/17.
 */
public class DisjointSet<T> implements Iterable<SetNode<T>> {

    private ArrayList<SetNode<T>> set;

    public DisjointSet(){
        this.set = new ArrayList<SetNode<T>>();
    }

    /**
     * adds an item to the disjoint set
     * @param item the item that is to be added
     */
    public void add(T item){
        makeSet(item);
    }

    /**
     * adds an item to the disjoint set and adds it to the set that the item "member" belongs to
     * @param item the item that is to be added
     * @param member any member of the set which the item should to be added to
     */
    public void add(T item, T member){
        makeSet(item);
        union(item, member);
    }

    /**
     * Merges the sets of the items "a" and "b". If both belong to the same set, nothing happens.
     * @param a any member of the first set
     * @param b any member of the second set
     */
    public void union(T a, T b){
        SetNode<T> nodeA = findNode(a);
        SetNode<T> nodeB = findNode(b);

        SetNode<T> rootA = nodeA.parent;
        SetNode<T> rootB = nodeB.parent;

        while (!rootA.equals(nodeA)){
            nodeA = rootA;
            rootA = nodeA.parent;
        }

        while (!rootB.equals(nodeB)){
            nodeB = rootB;
            rootB = nodeB.parent;
        }

        if(rootA.rank > rootB.rank){
            rootB.parent = rootA;
            rootB.rank = 0;
        }else if(rootB.rank > rootA.rank){
            rootA.parent = rootB;
            rootA.rank = 0;
        }else {
            rootB.parent = rootA;
            rootB.rank = 0;
            rootA.rank++;
        }
    }

    /**
     * finds out to which set a certain item belongs to and returns it
     * @return parent of the set to which the item belongs to
     */
    public T findSet(T item){
        SetNode<T> start = findNode(item);

        // update the reference so that the next query is faster
        start.parent = findSet(start);

        return start.parent.item;
    }

    /**
     * finds out to which set a certain item belongs to and returns it
     * @return parent SetNode of the set to which the item belongs to
     */
    private SetNode<T> findSet(SetNode<T> node){
        SetNode<T> parent = node.parent;

        if(!parent.equals(node)){
            // update the reference
            node.parent = findSet(parent);
            return node.parent;
        }

        return parent;
    }

    /**
     * create a new set that can be united with other sets
     */
    private void makeSet(T item){
        SetNode<T> node = new SetNode(item);
        this.set.add(node);
    }

    /**
     * Finds the node containing the item and returns it
     * @param item
     * @return node containing the item
     */
    private SetNode<T> findNode(T item){
        for(SetNode<T> node : set){
            if(node.item.equals(item)){
                return node;
            }
        }

        return null;
    }

    /**
     * make iterating through the set easier
     */
    @Override
    public Iterator<SetNode<T>> iterator() {
        Iterator<SetNode<T>> iterator = new Iterator<SetNode<T>>() {
            private int index = -1;

            @Override
            public boolean hasNext() {
                return index < set.size()-1;
            }

            @Override
            public SetNode<T> next() {
                index++;
                return set.get(index);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Can't remove for now...");
            }

        };

        return iterator;
    }

}
