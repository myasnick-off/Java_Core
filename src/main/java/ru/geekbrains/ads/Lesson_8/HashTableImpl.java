package ru.geekbrains.ads.Lesson_8;

import java.util.HashSet;
import java.util.Set;

public class HashTableImpl<K, V> implements HashTable<K, V> {

    static class Item<K, V> implements Entry<K, V> {

        private final K key;
        private V value;

        public Item(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Item{" + "key=" + key + ", value=" + value + '}';
        }
    }

    private final Set<Item<K, V>>[] data;

    private int size;

    public HashTableImpl(int initialCapacity) {
        this.data = new HashSet[initialCapacity * 2];
    }

    // хэш-функция для вычисления индекса массива по ключу
    private int hashFunc(K key) {
        return key.hashCode() % data.length;
    }

    @Override
    public boolean put(K key, V value) {
        if (size() == data.length) {
            return false;
        }
        int index = hashFunc(key);

        if (data[index] == null) {
            data[index] = new HashSet<>();
        } else {
            for (Item<K, V> item : data[index]) {
                if (isKeysEquals(item, key)) {
                    item.setValue(value);
                    return true;
                }
            }
        }
        data[index].add(new Item<>(key, value));
        size++;
        return true;
    }

    // метод сравнения ключей
    private boolean isKeysEquals(Item<K, V> item, K key) {
        return item.getKey() == null ? key == null : item.getKey().equals(key);
    }

    // метод возвращает индекс элемента по его ключу
    private int indexOf(K key) {
        int index = hashFunc(key);
        Set<Item<K, V>> itemSet = data[index];

        if (itemSet == null) {
            return -1;
        } else {
            for (Item<K, V> item : itemSet) {
                if (isKeysEquals(item, key)) {
                    return index;
                }
            }
        }
        return -1;
    }

    @Override
    public V get(K key) {
        int index = indexOf(key);
        return index == -1 ? null : valueOf(key, index);
    }

    // метод возвращает значение по его ключу и индексу в массиве
    private V valueOf(K key, int index) {
        for (Item<K, V> item : data[index]) {
            if (isKeysEquals(item, key)) {
                return item.getValue();
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int index = indexOf(key);

        if (index == -1) {
            return null;
        }
        for (Item<K, V> item : data[index]) {
            if (isKeysEquals(item, key)) {
                data[index].remove(item);
                size--;
                return item.getValue();
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void display() {
        System.out.println("---------------");
        for (int i = 0; i < data.length; i++) {
            System.out.printf("%d = [%s]%n", i, data[i]);
        }
        System.out.println("---------------");
    }
}