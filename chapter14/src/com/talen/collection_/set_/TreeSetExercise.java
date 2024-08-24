package com.talen.collection_.set_;

import java.util.TreeSet;

public class TreeSetExercise {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        TreeSet treeSet = new TreeSet();
        treeSet.add("abc");
        treeSet.add("ff");
        treeSet.add("a");
        System.out.println(treeSet);

        /**
         * public TreeSet(Comparator<? super E> comparator) {
         *         this(new TreeMap<>(comparator));
         *     }
         *
         * public boolean add(E e) {
         *         return m.put(e, PRESENT)==null;
         *     }
         *
         * public V put(K key, V value) {
         *         return put(key, value, true);
         *     }
         *
         * private V put(K key, V value, boolean replaceOld) {
         *         Entry<K,V> t = root;
         *         if (t == null) {
         *             addEntryToEmptyMap(key, value);
         *             return null;
         *         }
         *         int cmp;
         *         Entry<K,V> parent;
         *         // split comparator and comparable paths
         *         Comparator<? super K> cpr = comparator;
         *         if (cpr != null) {
         *             do {
         *                 parent = t;
         *                 cmp = cpr.compare(key, t.key);
         *                 if (cmp < 0)
         *                     t = t.left;
         *                 else if (cmp > 0)
         *                     t = t.right;
         *                 else {
         *                     V oldValue = t.value;
         *                     if (replaceOld || oldValue == null) {
         *                         t.value = value;
         *                     }
         *                     return oldValue;
         *                 }
         *             } while (t != null);
         *         } else {
         *             Objects.requireNonNull(key);
         *             @SuppressWarnings("unchecked")
         *             Comparable<? super K> k = (Comparable<? super K>) key;
         *             do {
         *                 parent = t;
         *                 cmp = k.compareTo(t.key);
         *                 if (cmp < 0)
         *                     t = t.left;
         *                 else if (cmp > 0)
         *                     t = t.right;
         *                 else {
         *                     V oldValue = t.value;
         *                     if (replaceOld || oldValue == null) {
         *                         t.value = value;
         *                     }
         *                     return oldValue;
         *                 }
         *             } while (t != null);
         *         }
         *         addEntry(key, value, parent, cmp < 0);
         *         return null;
         *     }
         *
         *  private void addEntry(K key, V value, Entry<K, V> parent, boolean addToLeft) {
         *         Entry<K,V> e = new Entry<>(key, value, parent);
         *         if (addToLeft)
         *             parent.left = e;
         *         else
         *             parent.right = e;
         *         fixAfterInsertion(e);
         *         size++;
         *         modCount++;
         *     }
         */
    }
}
