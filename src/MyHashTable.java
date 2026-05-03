public class MyHashTable<K, V> {

    // Переименовали класс узла в Entry (часто используется в Java)
    private class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "[" + key + "=" + value + "]";
        }
    }

    private Entry<K, V>[] buckets; // Изменили название массива
    private int numBuckets = 11; // Переименовали M
    private int size;

    public MyHashTable() {
        this.buckets = (Entry<K, V>[]) new Entry[numBuckets];
        this.size = 0;
    }

    public MyHashTable(int numBuckets) {
        this.numBuckets = numBuckets;
        this.buckets = (Entry<K, V>[]) new Entry[numBuckets];
        this.size = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % numBuckets);
    }

    public void put(K key, V value) {
        int index = hash(key);
        Entry<K, V> curr = buckets[index];

        while (curr != null) {
            if (curr.key.equals(key)) {
                curr.value = value;
                return;
            }
            curr = curr.next;
        }

        // Вставляем новый элемент
        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = buckets[index];
        buckets[index] = newEntry;
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        Entry<K, V> curr = buckets[index];

        while (curr != null) {
            if (curr.key.equals(key)) {
                return curr.value;
            }
            curr = curr.next;
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        Entry<K, V> curr = buckets[index];
        Entry<K, V> previous = null;

        while (curr != null) {
            if (curr.key.equals(key)) {
                if (previous == null) {
                    buckets[index] = curr.next;
                } else {
                    previous.next = curr.next;
                }
                size--;
                return curr.value;
            }
            previous = curr;
            curr = curr.next;
        }
        return null;
    }

    public boolean contains(V value) {
        for (Entry<K, V> bucket : buckets) {
            Entry<K, V> curr = bucket;
            while (curr != null) {
                if (curr.value.equals(value)) {
                    return true;
                }
                curr = curr.next;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (int i = 0; i < numBuckets; i++) {
            Entry<K, V> curr = buckets[i];
            while (curr != null) {
                if (curr.value.equals(value)) {
                    return curr.key;
                }
                curr = curr.next;
            }
        }
        return null;
    }

    // Метод для тестирования (для Part 1.2)
    public void showBucketDistribution() {
        for (int i = 0; i < numBuckets; i++) {
            int itemsCount = 0;
            Entry<K, V> temp = buckets[i];
            while (temp != null) {
                itemsCount++;
                temp = temp.next;
            }
            System.out.println("Bucket #" + i + " contains: " + itemsCount + " items");
        }
    }
}