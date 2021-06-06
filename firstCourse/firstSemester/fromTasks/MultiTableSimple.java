package firstSemester.fromTasks;

public class MultiTableSimple {
    public static void main(String[] args) {
        if (args.length == 1) {
            int n = Integer.parseInt(args[0]);
            int c1 = 0;
            int c2 = 0;
            int l1;
            int l2;
            if (n > 0) {
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        l1 = j * n;
                        while (l1 > 0) {
                            l1 = l1 / 10;
                            c1 += 1;
                        }
                        l2 = i * j;
                        System.out.print(l2);
                        while (l2 > 0) {
                            l2 = l2 / 10;
                            c2 += 1;
                        }
                        for (int k = c1 - c2; k > 0; k--) {
                            System.out.print(" ");
                        }
                        if (j < n) {
                            System.out.print(" ");
                        }
                        c1 = 0;
                        c2 = 0;
                    }
                    System.out.println();
                }
            } else {
                System.out.println("Please enter correct maximum multiplier.");
            }
        } else {
            System.out.println("Please enter maximum multiplier.");
        }
    }
}
