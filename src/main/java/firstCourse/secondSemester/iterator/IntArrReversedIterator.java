package firstCourse.secondSemester.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntArrReversedIterator implements Iterator<Integer> {
    private final Integer[] arr;
    private int cur;

    public IntArrReversedIterator(Integer[] arr) {
        this.arr = arr;
        this.cur = arr.length - 1;
    }

    @Override
    public boolean hasNext() {
        return this.cur >= 0;
    }

    @Override
    public Integer next() {
        if (this.cur >= 0) return arr[cur--];
        throw new NoSuchElementException("Bitte Kitte");
    }
}
