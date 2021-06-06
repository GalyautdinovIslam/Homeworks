package FirstSemester.other;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Notes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        int n = 1;
        try (FileWriter logs = new FileWriter("FirstCourse/FirstSemester/other/logs.txt", false)) {
            while (flag) {
                print("Enter maximum number of notes(number must be more than 0).");
                n = Integer.parseInt(sc.nextLine());
                if (n > 0) {
                    flag = false;
                    logs.write("Maximum number of notes = " + n + ".");
                    editor(logs);
                    logs.flush();
                }
            }
            String[] notes = new String[n];
            int counter = 0;
            int index;
            while (true) {
                print("Please enter command:");
                print("Enter \"help\" for view commands.");
                String command = sc.nextLine();
                switch (command) {
                    case "add":
                        if (counter < n) {
                            add(counter, notes, sc, logs);
                            counter++;
                        } else {
                            print("Array is full.");
                            print("");
                        }
                        break;
                    case "remove":
                        if (counter > 0) {
                            remove(counter, notes, logs);
                            counter--;
                        } else {
                            print("Array is empty");
                            print("");
                        }
                        break;
                    case "find":
                        index = find(notes, n, logs);
                        if (index > -1) {
                            print("Index: " + index);
                            print(notes[index]);
                        } else {
                            print("No notes");
                        }
                        print("");
                        break;
                    case "help":
                        print("");
                        print("add - add new note");
                        print("remove - remove last note");
                        print("find - find the longest note that was added");
                        print("exit - turning off the program");
                        print("");
                        break;
                    case "exit":
                        print("Bye");
                        System.exit(0);
                        break;
                    default:
                        print("Unknown command! Try again.");
                        print("");
                }
            }
        } catch (IOException ex) {
            print(ex.getMessage());
        }
    }

    public static void add(int counter, String[] notes, Scanner sc, FileWriter logs) {
        try {
            print("Enter a note:");
            notes[counter] = sc.nextLine();
            logs.write("Add");
            editor(logs);
            logs.write(notes[counter]);
            editor(logs);
            logs.flush();
            print("Saved.");
            print("");
        } catch (IOException ex) {
            print(ex.getMessage());
        }
    }

    public static void remove(int counter, String[] notes, FileWriter logs) {
        try {
            notes[counter - 1] = null;
            logs.write("Remove");
            editor(logs);
            logs.flush();
            print("Last note was deleted.");
            print("");
        } catch (IOException ex) {
            print(ex.getMessage());
        }
    }

    public static int find(String[] notes, int n, FileWriter logs) {
        try {
            logs.write("find");
            editor(logs);
            logs.flush();
            int maxindex = -1;
            int maxlength = 0;
            for (int i = 0; i < n; i++) {
                if (notes[i] == null) {
                    break;
                }
                if (notes[i].length() > maxlength) {
                    maxindex = i;
                    maxlength = notes[i].length();
                }
            }
            return maxindex;
        } catch (IOException ex) {
            print(ex.getMessage());
        }
        return -1;
    }

    public static void print(String str) {
        System.out.println(str);
    }

    public static void editor(FileWriter logs) {
        try {
            logs.append('\n');
            logs.write("-----------------------------------------");
            logs.append('\n');
            logs.flush();
        } catch (IOException ex) {
            print(ex.getMessage());
        }
    }
}