package FirstSemester;

public class MyIndexOf {
    public static void main(String[] args) {
        if (args.length == 2) {
            String s1 = args[0];
            String s2 = args[1];
            int ls1 = s1.length();
            int ls2 = s2.length();
            if (ls1 >= ls2) {
                char[] s1ToChar = s1.toCharArray();
                char[] s2ToChar = s2.toCharArray();
                boolean flag = true;
                int i = 0;
                int j = 0;
                int savei = -1;
                while (i < ls1 && flag) {
                    if (s1ToChar[i] == s2ToChar[j]) {
                        for (int k = 1; k < ls2; k++) {
                            if (s1ToChar[i + k] != s2ToChar[j + k]) {
                                i += 1;
                                break;
                            } else if (k == ls2 - 1) {
                                savei = i;
                                flag = false;
                            }
                        }
                    } else {
                        i += 1;
                    }
                }
                if (savei > -1) {
                    System.out.println("Was found in " + savei + " characters."); //or savei
                } else {
                    System.out.println("Wasn't found."); //or -1
                }
            } else {
                System.out.println("Main string should be more than string you want to find.");
            }
        } else {
            System.out.println("Please enter the main string and the string you want to find.");
        }
    }
}