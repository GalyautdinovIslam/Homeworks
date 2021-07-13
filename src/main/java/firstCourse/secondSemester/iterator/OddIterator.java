package firstCourse.secondSemester.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OddIterator implements Iterator<Integer> {
    private final Integer[] arr;
    private int cur = -1;

    public OddIterator(Integer[] arr) {
        this.arr = arr;
        this.cur = newCur(arr, this.cur);
    }

    @Override
    public boolean hasNext() {
        return this.cur < arr.length;
    }

    @Override
    public Integer next() {
        if (this.cur < arr.length) {
            int tmp = cur;
            cur = this.newCur(arr, cur);
            return arr[tmp];
        }
        throw new NoSuchElementException("Bitte Kitte");
    }

    private int newCur(Integer[] arr, int cur) {//
        for (int i = cur + 1; i < arr.length; i++) {
            if (arr[i] % 2 == 1) {
                return i;
            }
        }
        return arr.length;
    }
}
