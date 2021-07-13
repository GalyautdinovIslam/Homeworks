package firstCourse.firstSemester.fromTasks;

import java.util.Scanner;

public class PlacingBrackets {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int count = 0;
        int indexOpen = -1;
        int indexClose = -1;
        boolean flagClose = true;
        boolean flagOpen = true;
        if (search(str, indexOpen, indexClose, count, flagOpen, flagClose) == 0) {
            System.out.println("Right.");
        } else {
            System.out.println("Wrong.");
        }
    }

    public static int search(String str, int indexOpen, int indexClose, int count, boolean flagOpen, boolean flagClose) {
        if (flagClose) {
            indexClose = str.indexOf(")", indexClose + 1);
        }
        if (flagOpen) {
            indexOpen = str.indexOf("(", indexOpen + 1);
        }
        if (indexClose > -1 && flagClose) {
            count -= 1;
        } else {
            flagClose = false;
        }
        if (indexOpen > -1 && flagOpen) {
            count += 1;
        } else {
            flagOpen = false;
        }
        if (count < 0 || (indexClose < indexOpen && indexClose != -1)) {
            return -1;
        }
        if (flagClose || flagOpen) {
            return search(str, indexOpen, indexClose, count, flagOpen, flagClose);
        }
        return count;
    }
}
