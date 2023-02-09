package lesson5;

// key   - int
// value - V
public class XMap<V> {

    private int[] keys;
    private V[] data;
    private int count = 0;
    @SuppressWarnings("unchecked")
    public XMap(int size) {
        this.keys = new int[size];
        this.data = (V[]) new Object[size];
    }

    private int keyToIndex(int key) {
        return key % data.length;
    }

    private void autogrow() {
        // allocate more
        // iterate source
        //  - read
        //  - write to new array
    }

    public void put(int key, V value) {
        if (((double)count) / data.length > 0.7) autogrow();
        int index = keyToIndex(key);
        // TODO: check whether is FREE
        if (data[index] == null) {
            keys[index] = key;
            data[index] = value;
        } else {
            // TODO: go right (index++), look for empty cell
            //  while (delta < 3) {
            //    index++
            //    delta++
            //  }
            //  if (delta > 3) autogrow() => O(3)
            throw new RuntimeException("duplicate");
        }
        //  if NO => go right (index++) until FREE
        count++;
    }

    public V get(int key) {
        int index = keyToIndex(key);
        if (keys[index] == key) return data[index];
        // TODO: go right (index++), look for my key
        throw new RuntimeException("not mine");
    }

}

