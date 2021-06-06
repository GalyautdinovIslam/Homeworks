package FirstSemester.fromTasks;

public class MultiTable {
    public static void main(String[] args) {
        if (args.length == 1) {
            int n = Integer.parseInt(args[0]);
            int l1 = 0;
            int c1 = 0;
            int l2 = 0;
            int c2 = 0;
            int c3 = 0;
            boolean f = false;
            if (n > 0) {
                for (int i = 0; i <= n; i++) {
                    for (int j = 0; j <= n; j++) {
                        if (i == 0) {
                            if (j == 0) {
                                System.out.print("X");
                                c3 += 1;
                                l1 = n;
                                while (l1 > 0) {
                                    l1 = l1 / 10;
                                    c1 += 1;
                                }
                                while (c1 - 1 > 0) {
                                    System.out.print(" ");
                                    c3 += 1;
                                    c1 -= 1;
                                }
                                System.out.print("| ");
                                c3 += 2;
                                c1 = 0;
                            } else {
                                l1 = j * n;
                                while (l1 > 0) {
                                    l1 = l1 / 10;
                                    c1 += 1;
                                }
                                l2 = j;
                                System.out.print(l2);
                                while (l2 > 0) {
                                    l2 = l2 / 10;
                                    c3 += 1;
                                    c2 += 1;
                                }
                                if (j < n) {
                                    for (int k = 0; k < (c1 - c2); k++) {
                                        System.out.print(" ");
                                        c3 += 1;
                                    }
                                    System.out.print(" ");
                                    c3 += 1;
                                }
                                c1 = 0;
                                c2 = 0;
                            }
                        } else {
                            if (i == 1) {
                                while (c3 >= 0) {
                                    System.out.print("-");
                                    c3 -= 1;
                                    f = true;
                                }
                                if (f) {
                                    System.out.println();
                                    f = false;
                                }
                                if (j == 0) {
                                    l1 = n;
                                    while (l1 > 0) {
                                        l1 = l1 / 10;
                                        c1 += 1;
                                    }
                                    l2 = i;
                                    System.out.print(i);
                                    while (l2 > 0) {
                                        l2 = l2 / 10;
                                        c2 += 1;
                                    }
                                    for (int k = 0; k < (c1 - c2); k++) {
                                        System.out.print(" ");
                                    }
                                    System.out.print("| ");
                                    c1 = 0;
                                    c2 = 0;
                                } else {
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
                                    if (j < n) {
                                        for (int k = 0; k < (c1 - c2); k++) {
                                            System.out.print(" ");
                                        }
                                        System.out.print(" ");
                                    }
                                    c1 = 0;
                                    c2 = 0;
                                }
                            } else {
                                if (j == 0) {
                                    l1 = n;
                                    while (l1 > 0) {
                                        l1 = l1 / 10;
                                        c1 += 1;
                                    }
                                    l2 = i;
                                    System.out.print(i);
                                    while (l2 > 0) {
                                        l2 = l2 / 10;
                                        c2 += 1;
                                    }
                                    for (int k = 0; k < (c1 - c2); k++) {
                                        System.out.print(" ");
                                    }
                                    System.out.print("| ");
                                    c1 = 0;
                                    c2 = 0;
                                } else {
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
                                    if (j < n) {
                                        for (int k = 0; k < (c1 - c2); k++) {
                                            System.out.print(" ");
                                        }
                                        System.out.print(" ");
                                    }
                                    c1 = 0;
                                    c2 = 0;
                                }
                            }
                        }
                    }
                    System.out.println();
                }
            } else {
                System.out.println("Please enter correct value of maximum multiplier.");
            }
        } else {
            System.out.println("Please enter maximum multiplier.");
        }
    }
}
