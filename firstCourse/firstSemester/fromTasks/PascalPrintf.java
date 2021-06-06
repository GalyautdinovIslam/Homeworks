package firstSemester.fromTasks;

import java.util.Scanner;

public class PascalPrintf {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] array = new int[n + 1][2 * n + 3];
        array[0][n + 1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < 2 * n + 2; j++) {
                array[i][j] = array[i - 1][j - 1] + array[i - 1][j + 1];
            }
        }
        int max = Math.max((int) Math.log10(array[n][n + 1]), (int) Math.log10(array[n][n + 2])) + 1;
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j < 2 * n + 2; j++) {
                if (array[i][j] == 0) {
                    System.out.printf("%" + (max + 2) + "s", " ");
                } else {
                    System.out.print(array[i][j]);
                    System.out.printf("%" + (max - (int) Math.log10(array[i][j]) + 1) + "s", " ");
                }
            }
            System.out.printf("\n");
        }
    }
}
