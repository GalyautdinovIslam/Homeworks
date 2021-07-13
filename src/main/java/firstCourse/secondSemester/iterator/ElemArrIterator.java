package firstCourse.secondSemester.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ElemArrIterator<E> implements Iterator<E> {
    private final E[] arr;
    private int cur;

    public ElemArrIterator(E[] arr) {
        this.arr = arr;
        this.cur = 0;
    }

    @Override
    public boolean hasNext() {
        return this.cur < arr.length;
    }

    @Override
    public E next() {
        if (this.cur < arr.length) return arr[cur++];
        throw new NoSuchElementException("Bitte Kitte");
    }
}
