package firstCourse.secondSemester.mySortedSet;

import java.util.*;

public class MySortedSet<E> extends AbstractSet<E> implements SortedSet<E> {
    private static final int DEFAULT_SIZE = 8;
    private static final int COEFFICIENT_FOR_NEW_ARRAY = 2;

    private E[] data;
    private int size;
    private final Comparator<? super E> comparator;

    private MySortedSet<E> root;
    private MySortedSet<MySortedSet<E>> children;

    public MySortedSet(Comparator<? super E> comparator) {
        this.data = (E[]) new Object[DEFAULT_SIZE];
        this.size = 0;
        this.comparator = comparator;
    }

    public MySortedSet(Collection<? extends E> col, Comparator<? super E> comparator) {
        E[] arr = (E[]) col.toArray();
        Arrays.sort(arr, comparator);
        this.data = (E[]) new Object[arr.length];
        this.size = 0;
        E previous = null;
        for (int i = 0; i < arr.length; i++) {
            if (i == 0 && arr[i] != null) previous = data[this.size++] = arr[i];
            else {
                if (comparator.compare(arr[i], previous) != 0 && arr[i] != null) previous = data[this.size++] = arr[i];
            }
        }
        this.comparator = comparator;
    }

    private E[] newData() {
        E[] newData = (E[]) new Object[COEFFICIENT_FOR_NEW_ARRAY * data.length];
        if (this.size >= 0) System.arraycopy(this.data, 0, newData, 0, this.size);
        return newData;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int cur = 0;
            private boolean flag = false;

            @Override
            public boolean hasNext() {
                return cur < size;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    flag = true;
                    return data[cur++];
                }
                throw new NoSuchElementException("Set is ended.");
            }

            @Override
            public void remove() {
                if (flag) {
                    size--;
                    if (size-- - cur >= 0) System.arraycopy(data, --cur + 1, data, --cur, size-- - cur);
                    flag = false;
                } else throw new IllegalStateException("Next method wasn't invoked.");
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[this.size];
        System.arraycopy(this.data, 0, arr, 0, this.size);
        return arr;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a == null) throw new NullPointerException("Your array can't be null");
        try {
            if (a.length < this.size) {
                T[] arr = (T[]) new Object[this.size];
                for (int i = 0; i < this.size; i++) {
                    arr[i] = (T) this.data[i];
                }
                return arr;
            } else {
                for (int i = 0; i < this.size; i++) {
                    a[i] = (T) this.data[i];
                }
                if (a.length > this.size) a[this.size] = null;
                return a;
            }
        } catch (ClassCastException e) {
            throw new ArrayStoreException("the runtime type of the specified array is not a supertype of the runtime" +
                    " type of elements in this collection");
        }
    }

    @Override
    public boolean add(E e) {
        if (e == null) throw new NullPointerException("You can't add null");

        int index = 0;
        for (int i = 0; i < this.size; i++) {
            if (this.comparator.compare(e, this.data[i]) == 0) return false; //не используется contains, т.к так быстрее
            if (this.comparator.compare(e, this.data[i]) > 0) index++;
            else break;
        }

        if (this.size == data.length) this.data = newData();

        if (this.size - index >= 0) System.arraycopy(this.data, index, this.data, index + 1, this.size - index);
        this.data[index] = e;
        this.size++;
        if (this.root != null) root.add(e);
        if (this.children != null) {
            for (MySortedSet<E> x : this.children) {
                x.add(e);
            }
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) throw new NullPointerException("You can't remove null");

        if (!this.contains(o)) return false;
        E el = (E) o;
        int index = 0;
        for (int i = 0; i < this.size; i++) {
            if (this.comparator.compare(el, this.data[i]) == 0) {
                index = i;
                break;
            }
        }
        if (index == this.size - 1) this.data[index] = null;
        else {
            if (this.size - 1 - index >= 0)
                System.arraycopy(this.data, index + 1, this.data, index, this.size - 1 - index);
        }
        this.size--;

        if (this.root != null) root.remove(o);
        if (this.children != null) {
            for (MySortedSet<E> x : this.children) {
                x.remove(o);
            }
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean flag = true;
        for (Object x : c) {
            if (!this.contains(x)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) throw new NullPointerException("You can't add null");
        boolean flag = false;
        for (E x : c) {
            if (this.add(x)) flag = true;
        }
        if (flag) {
            if (this.root != null) root.addAll(c);
            if (this.children != null) {
                for (MySortedSet<E> x : this.children) {
                    x.addAll(c);
                }
            }
        }
        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) throw new NullPointerException("You can't retain null");
        boolean flag = false;
        for (int i = 0; i < this.size; i++) {
            if (!c.contains(this.data[i])) flag = this.remove(this.data[i]);
        }
        if (flag) {
            if (this.root != null) root.retainAll(c);
            if (this.children != null) {
                for (MySortedSet<E> x : this.children) {
                    x.retainAll(c);
                }
            }
        }
        return flag;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) throw new NullPointerException("You can't remove null");
        boolean flag = false;
        for (Object x : c) {
            if (this.remove(x)) flag = true;
        }
        if (flag) {
            if (this.root != null) root.removeAll(c);
            if (this.children != null) {
                for (MySortedSet<E> x : this.children) {
                    x.removeAll(c);
                }
            }
        }
        return flag;
    }

    @Override
    public void clear() {
        this.data = (E[]) new Object[this.size];
        this.size = 0;
        if (this.root != null) root.clear();
        if (this.children != null) {
            for (MySortedSet<E> x : this.children) {
                x.clear();
            }
        }
        root = null;
        children = null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (this.size == 0 || o == null) return false;
        E el = (E) o;
        for (int i = 0; i < this.size; i++) {
            if (this.comparator.compare(el, this.data[i]) < 0) return false;
            if (this.comparator.compare(el, this.data[i]) == 0) return true;
        }
        return false;
    }

    @Override
    public Comparator<? super E> comparator() {
        return this.comparator;
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        if (fromElement == null || toElement == null) throw new NullPointerException("Arguments can't be null");

        if (this.comparator.compare(fromElement, toElement) > 0 ||
                this.comparator.compare(fromElement, this.first()) < 0 ||
                this.comparator.compare(fromElement, this.last()) > 0 ||
                this.comparator.compare(toElement, this.first()) <= 0 ||
                this.comparator.compare(toElement, this.last()) > 0)
            throw new IllegalArgumentException("Argument lies outside the bounds of the range.");

        MySortedSet<E> newSet = new MySortedSet<>(this.comparator);
        if (this.comparator.compare(fromElement, toElement) == 0) return newSet;

        for (int i = 0; i < this.size; i++) {
            if (this.comparator.compare(this.data[i], fromElement) >= 0 &&
                    this.comparator.compare(this.data[i], toElement) < 0) {
                newSet.add(this.data[i]);
            }
            if (this.comparator.compare(this.data[i], toElement) >= 0) break;
        }
        newSet.setRoot(this);
        if (this.children == null) this.children = new MySortedSet<>(null);
        this.children.add(newSet);
        return newSet;
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        if (toElement == null) throw new NullPointerException("toElement can't be null");
        if (this.comparator.compare(toElement, this.first()) <= 0 ||
                this.comparator.compare(toElement, this.last()) > 0)
            throw new IllegalArgumentException("Argument lies outside the bounds of the range.");

        MySortedSet<E> newSet = new MySortedSet<>(this.comparator);
        for (int i = 0; i < this.size; i++) {
            if (this.comparator.compare(this.data[i], toElement) < 0) {
                newSet.add(this.data[i]);
            }
            if (this.comparator.compare(this.data[i], toElement) >= 0) break;
        }

        newSet.setRoot(this);
        if (this.children == null) this.children = new MySortedSet<>(null);
        this.children.add(newSet);
        return newSet;
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        if (fromElement == null) throw new NullPointerException("fromElement can't be null");
        if (this.comparator.compare(fromElement, this.first()) <= 0 ||
                this.comparator.compare(fromElement, this.last()) > 0)
            throw new IllegalArgumentException("Argument lies outside the bounds of the range.");

        MySortedSet<E> newSet = new MySortedSet<>(this.comparator);
        for (int i = 0; i < this.size; i++) {
            if (this.comparator.compare(this.data[i], fromElement) >= 0) {
                newSet.add(this.data[i]);
            }
        }

        newSet.setRoot(this);
        if (this.children == null) this.children = new MySortedSet<>(null);
        this.children.add(newSet);
        return newSet;
    }

    @Override
    public E first() {
        if (this.isEmpty()) throw new NoSuchElementException("Set is empty");
        return this.data[0];
    }

    @Override
    public E last() {
        if (this.isEmpty()) throw new NoSuchElementException("Set is empty");
        return this.data[this.size - 1];
    }

    public void setRoot(MySortedSet<E> root) {
        this.root = root;
    }
}
