package FirstSemester.fromTasks;

import java.util.Scanner;

public class ArraySearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the length of the first array:");
        int n = sc.nextInt();
        System.out.println("Enter array elements separately:");
        String[] first = new String[n];
        for (int i = 0; i < n; i++) {
            first[i] = sc.next();
        }
        System.out.println("Enter the length of the second array:");
        int m = sc.nextInt();
        if (m < n) {
            System.out.println("The first array must be less than the second array.");
        } else {
            System.out.println("Enter array elements separately:");
            String[] second = new String[m];
            for (int i = 0; i < m; i++) {
                second[i] = sc.next();
            }
            //System.out.println(Arrays.toString(first));
            //System.out.println(Arrays.toString(second));
            int save = -1;
            int i = 0;
            int j = 0;
            boolean flag = true;
            while (i < m && flag) {
                if (second[i].equals(first[j])) {
                    for (int k = 1; k < n; k++) {
                        if (!second[i + k].equals(first[j + k])) {
                            i += 1;
                            break;
                        } else if (k == n - 1) {
                            save = i;
                            flag = false;
                        }
                    }
                } else {
                    i += 1;
                }
            }
            if (save == -1) {
                System.out.println("Wasn't found.");
            } else {
                System.out.println(save);
            }
        }
    }
}
