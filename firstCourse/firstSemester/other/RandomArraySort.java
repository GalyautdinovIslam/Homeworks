package firstSemester.other;

import java.util.Arrays;

public class RandomArraySort {
    public static void main(String[] args) {
        int[] array = new int[10];
        int b = 30;
        int a = 2;
        int rubbish;
        int min = 30;
        int num = 0;
        int i;
        int j;
        double e = 1d;
        for (i = 0; i < 10; i++) {
            array[i] = (int) (Math.random() * (b - a) + a);
        }
        System.out.print(Arrays.toString(array));
        System.out.println();
        for (i = 0; i < 10; i++) {
            for (j = i; j < 10; j++) {
                if (array[j] < min) {
                    min = array[j];
                    num = j;
                }
            }
            rubbish = array[i];
            array[i] = array[num];
            array[num] = rubbish;
            min = 30;
        }
        System.out.print(Arrays.toString(array));
        System.out.println();
        for (i = 0; i < 10; i++) {
            e *= array[i];
        }
        System.out.println(Math.pow(e, 0.1));
    }
}
