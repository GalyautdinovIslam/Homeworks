package firstCourse.secondSemester.ModifiableCollection;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ModifiableCollection<T> extends AbstractCollection<T> {
    private final static int DEFAULT_SIZE = 8;
    private T[] data;
    private int size;

    public ModifiableCollection() {
        data = (T[]) new Object[DEFAULT_SIZE];
        size = 0;
    }

    public ModifiableCollection(Collection<? extends T> col) {
        data = (T[]) new Object[col.size()];
        size = 0;
        for (T el : col) {
            data[size++] = el;
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new ForCollectionIterator();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(T el) {
        if (size == data.length) {
            T[] newData = (T[]) new Object[data.length * 2];
            int j = 0;
            for (T x : data) {
                newData[j++] = x;
            }
            data = newData;
        }
        data[size++] = el;
        return true;
    }

    private class ForCollectionIterator implements Iterator<T> {
        private int cur = 0;
        private boolean flag = false;

        @Override
        public boolean hasNext() {
            return cur < size;
        }

        @Override
        public T next() {
            if (hasNext()) {
                flag = true;
                return data[cur++];
            }
            throw new NoSuchElementException("Bitte Kitte");
        }

        @Override
        public void remove() {
            if (flag) {
                size--;
                if (size-- - cur >= 0) System.arraycopy(data, --cur + 1, data, --cur, size-- - cur);
                flag = false;
            } else {
                throw new IllegalStateException("next method wasn't used");
            }
        }
    }
}
