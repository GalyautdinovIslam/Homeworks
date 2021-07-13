package firstCourseTest.secondSemesterTest.MySortedSetTest;

import firstCourse.secondSemester.MySortedSet.MySortedSet;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class MySortedSetTest {

    @Test
    public void toArray() {
        MySortedSet<Integer> set;

        set = new MySortedSet<>(comp());
        toArrayTest(set);

        set = new MySortedSet<>(col(1, 1, 1), comp());
        toArrayTest(set);

        set = new MySortedSet<>(col(3, 1, 2), comp());
        toArrayTest(set);
    }

    private static void toArrayTest(MySortedSet<Integer> set) {
        Object[] arr = set.toArray();
        assertEquals(set.size(), arr.length);
        int j = 0;
        for (Integer i : set) {
            assertEquals(arr[j++], i);
        }
    }

    @Test
    public void toArrayWithArray() {
        MySortedSet<Integer> set;

        Integer[] a = new Integer[6];

        set = new MySortedSet<>(comp());
        Object[] arr = set.toArray(a);
        assertEquals(6, arr.length);
        int j = 0;
        for (Integer i : set) {
            assertEquals(arr[j++], i);
        }

        set = new MySortedSet<>(col(1, 2, 3), comp());
        arr = set.toArray(a);
        assertEquals(6, arr.length);
        assertNull(a[set.size()]);
        j = 0;
        for (Integer i : set) {
            assertEquals(arr[j++], i);
        }

        set = new MySortedSet<>(col(1, 2, 3, 4, 5, 6, 7), comp());
        arr = set.toArray(a);
        assertEquals(7, arr.length);
        j = 0;
        for (Integer i : set) {
            assertEquals(arr[j++], i);
        }

        set = new MySortedSet<>(col(1, 2, 3, 4, 5, 6), comp());
        arr = set.toArray(a);
        assertEquals(6, arr.length);
        j = 0;
        for (Integer i : set) {
            assertEquals(arr[j++], i);
        }
        try {
            arr = set.toArray(null);
        } catch (NullPointerException ex) {
            assertEquals("Your array can't be null", ex.getMessage());
        }
    }

    @Test
    public void add() {
        MySortedSet<Integer> set;

        set = new MySortedSet<>(col(5, 4, 2), comp());
        addTest(set, 1, true);
        addTest(set, 1, false);
        addTest(set, 4, false);

        set = new MySortedSet<>(comp());
        addTest(set, 1, true);
        addTest(set, 1, false);
        addTest(set, 4, true);
        try {
            addTest(set, null, false);
        } catch (NullPointerException ex) {
            assertEquals("You can't add null", ex.getMessage());
        }

        set = new MySortedSet<>(col(5, 1, 1), comp());
        addTest(set, 1, false);
        addTest(set, 3, true);
        addTest(set, 4, true);
    }

    private static void addTest(MySortedSet<Integer> set, Integer i, boolean flag) {
        int size = set.size();
        assertEquals(flag, set.add(i));
        if (flag) assertEquals(size + 1, set.size());
    }

    @Test
    public void remove() {
        MySortedSet<Integer> set;

        set = new MySortedSet<>(col(5, 4, 2), comp());
        removeTest(set, 5, true);
        removeTest(set, 5, false);
        removeTest(set, 1, false);


        set = new MySortedSet<>(comp());
        removeTest(set, 5, false);
        try {
            removeTest(set, null, false);
        } catch (NullPointerException ex) {
            assertEquals("You can't remove null", ex.getMessage());
        }
        removeTest(set, 0, false);

        set = new MySortedSet<>(col(5, 1, 1), comp());
        removeTest(set, 5, true);
        removeTest(set, 5, false);
        removeTest(set, 1, true);
        removeTest(set, 1, false);

    }

    private static void removeTest(MySortedSet<Integer> set, Object o, boolean flag) {
        int size = set.size();
        assertEquals(flag, set.remove(o));
        if (flag) assertEquals(size - 1, set.size());
    }

    @Test
    public void containsAll() {
        MySortedSet<Integer> set;

        set = new MySortedSet<>(col(5, 4, 2), comp());
        assertTrue(set.containsAll(col(4, 2, 5)));
        assertFalse(set.containsAll(col(4, 3, 5)));
        assertTrue(set.containsAll(col(4, 5)));
        assertFalse(set.containsAll(col(4, 2, 5, null)));

        set = new MySortedSet<>(comp());
        for (int i = 0; i < 10; i++) assertFalse(set.containsAll(col((int) (Math.random() * Integer.MAX_VALUE))));
    }

    @Test
    public void addAll() {
        MySortedSet<Integer> set;

        set = new MySortedSet<>(col(5, 4, 2), comp());
        assertTrue(set.addAll(col(5, 2, 4, 1)));
        assertFalse(set.addAll(col(5, 2, 4, 1)));
        assertTrue(set.addAll(col(0)));
        try {
            set.addAll(null);
        } catch (NullPointerException ex) {
            assertEquals("You can't add null", ex.getMessage());
        }
    }

    @Test
    public void retainAll() {
        MySortedSet<Integer> set;

        set = new MySortedSet<>(col(5, 4, 2), comp());
        assertFalse(set.retainAll(col(5, 2, 4, 1)));
        assertTrue(set.retainAll(col(5, 2, 1)));
        assertTrue(set.retainAll(col(0)));
        try {
            set.retainAll(null);
        } catch (NullPointerException ex) {
            assertEquals("You can't retain null", ex.getMessage());
        }
    }

    @Test
    public void removeAll() {
        MySortedSet<Integer> set;

        set = new MySortedSet<>(col(5, 4, 2), comp());
        assertFalse(set.removeAll(col(1)));
        assertTrue(set.removeAll(col(5, 2, 1)));
        assertFalse(set.removeAll(col(0)));
        assertTrue(set.removeAll(col(4, 0, 1)));
        try {
            set.removeAll(null);
        } catch (NullPointerException ex) {
            assertEquals("You can't remove null", ex.getMessage());
        }
    }

    @Test
    public void clear() {
        MySortedSet<Integer> set;

        set = new MySortedSet<>(col(5, 4, 2), comp());
        set.clear();
        assertEquals(0, set.size());

        set = new MySortedSet<>(comp());
        set.clear();
        assertEquals(0, set.size());

    }

    @Test
    public void size() {
        MySortedSet<Integer> set;

        set = new MySortedSet<>(col(5, 4, 2), comp());
        assertEquals(3, set.size());


        set = new MySortedSet<>(comp());
        assertEquals(0, set.size());

        set = new MySortedSet<>(col(5, 1, 1), comp());
        assertEquals(2, set.size());
    }

    @Test
    public void isEmpty() {
        MySortedSet<Integer> set;

        set = new MySortedSet<>(col(5, 4, 2), comp());
        assertFalse(set.isEmpty());

        set = new MySortedSet<>(col(5, 5, 5), comp());
        assertFalse(set.isEmpty());


        set = new MySortedSet<>(comp());
        assertTrue(set.isEmpty());

        set = new MySortedSet<>(col(5, 4, 2), comp());
        set.remove(5);
        assertFalse(set.isEmpty());
        set.remove(4);
        assertFalse(set.isEmpty());
        set.remove(2);
        assertTrue(set.isEmpty());
    }

    @Test
    public void contains() {
        MySortedSet<Integer> set;

        set = new MySortedSet<>(col(5, 4, 2), comp());
        assertFalse(set.contains(null));
        assertFalse(set.contains(3));
        assertTrue(set.contains(5));

        set = new MySortedSet<>(comp());
        for (int i = 0; i < 10; i++) assertFalse(set.contains((int) (Math.random() * Integer.MAX_VALUE)));
    }

    @Test
    public void subSet() {
        MySortedSet<Integer> set;
        MySortedSet<Integer> newSet;

        set = new MySortedSet<>(col(5, 4, 2, 1, 0), comp());
        newSet = (MySortedSet<Integer>) set.subSet(0, 3);
        assertEquals(3, newSet.size());
        newSet.add(3);
        assertEquals(4, newSet.size());
        assertEquals(6, set.size());
        newSet.add(5);
        assertEquals(5, newSet.size());
        assertEquals(6, set.size());
        try {
            newSet = (MySortedSet<Integer>) set.subSet(-1, 3);
        } catch (IllegalArgumentException ex) {
            assertEquals("Argument lies outside the bounds of the range.", ex.getMessage());
        }
    }

    @Test
    public void headSet() {
        MySortedSet<Integer> set;
        MySortedSet<Integer> newSet;

        set = new MySortedSet<>(col(5, 4, 2, 1, 0), comp());
        newSet = (MySortedSet<Integer>) set.headSet(3);
        assertEquals(3, newSet.size());
        newSet.add(3);
        assertEquals(4, newSet.size());
        assertEquals(6, set.size());
        newSet.add(5);
        assertEquals(5, newSet.size());
        assertEquals(6, set.size());
        try {
            newSet = (MySortedSet<Integer>) set.headSet(6);
        } catch (IllegalArgumentException ex) {
            assertEquals("Argument lies outside the bounds of the range.", ex.getMessage());
        }
    }

    @Test
    public void tailSet() {
        MySortedSet<Integer> set;
        MySortedSet<Integer> newSet;

        set = new MySortedSet<>(col(5, 4, 2, 1, 0), comp());
        newSet = (MySortedSet<Integer>) set.tailSet(1);
        assertEquals(4, newSet.size());
        newSet.add(3);
        assertEquals(5, newSet.size());
        assertEquals(6, set.size());
        newSet.add(5);
        assertEquals(5, newSet.size());
        assertEquals(6, set.size());
        try {
            newSet = (MySortedSet<Integer>) set.tailSet(0);
        } catch (IllegalArgumentException ex) {
            assertEquals("Argument lies outside the bounds of the range.", ex.getMessage());
        }
    }

    @Test
    public void first() {
        MySortedSet<Integer> set;

        set = new MySortedSet<>(col(5, 4, 2), comp());
        assertEquals(new Integer(2), set.first());

        set = new MySortedSet<>(comp());
        try {
            set.first();
        } catch (NoSuchElementException ex) {
            assertEquals("Set is empty", ex.getMessage());
        }

    }

    @Test
    public void last() {
        MySortedSet<Integer> set;

        set = new MySortedSet<>(col(5, 4, 2), comp());
        assertEquals(new Integer(5), set.last());

        set = new MySortedSet<>(comp());
        try {
            set.last();
        } catch (NoSuchElementException ex) {
            assertEquals("Set is empty", ex.getMessage());
        }

    }

    @Test
    public void comparator() {
        MySortedSet<Integer> set;

        Comparator<Integer> toSetComp = comp();
        set = new MySortedSet<>(col(5, 4, 2), toSetComp);
        assertEquals(toSetComp, set.comparator());
    }

    private Collection<Integer> col(Integer... ints) {
        return new ArrayList<>(Arrays.asList(ints));
    }

    private Comparator<Integer> comp() {
        return Comparator.comparingInt(o -> o);
    }
}