package firstCourse.secondSemester.decorator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        //decorators();
        //deSerialization();
        //mySerialization();
        //myDeserialization();
    }

    private static void decorators() {
//        Collection<Product> col = new ArrayList<>();
//        col.add(new Product("Table", 7999.99, 7));
//        col.add(new Product("Knife", 599.99, 38));
//        col.add(new Product("Sword", 3599.90, 2));
//        col.add(new Product("Honey", 350, 164));
//        col.add(new Product("Glass", 149.99, 1053));

//        try(ProductOutputStream pos = new ProductOutputStream(new FileOutputStream("src/main/resources/firstCourse/secondSemester/decorator/decoratorsData"))){
//            for(Product p: col){
//                pos.writeProduct(p);
//            }
//            pos.flush();
//        }catch(IOException ex){
//            ex.getStackTrace();
//        }

        try (ProductInputStream pis = new ProductInputStream(new FileInputStream("src/main/resources/firstCourse/secondSemester/decorator/decoratorsData"))) {
            Collection<Product> productCol = new ArrayList<>();
            while (pis.available() > 0) {
                productCol.add(pis.readProduct());
            }
            for (Product p : productCol) {
                System.out.println(p.toString());
            }
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }

    private static void deSerialization() {
//        Collection<Product> col = new ArrayList<>();
//        col.add(new Product("Table", 7999.99, 7));
//        col.add(new Product("Knife", 599.99, 38));
//        col.add(new Product("Sword", 3599.90, 2));
//        col.add(new Product("Honey", 350, 164));
//        col.add(new Product("Glass", 149.99, 1053));

//        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/firstCourse/secondSemester/decorator/deSerializationData"))){
//            for(Product p: col){
//                oos.writeObject(p);
//            }
//            oos.flush();
//        }catch (IOException ex){
//            ex.getStackTrace();
//        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/resources/firstCourse/secondSemester/decorator/deSerializationData"))) {
            while (true) {
                Product p = (Product) ois.readObject();
                System.out.println(p.toString());
            }
        } catch (ClassNotFoundException | IOException ex) {
            ex.getStackTrace();
        }
    }

    private static void mySerialization() {
        Collection<Product> col = new ArrayList<>();
        col.add(new Product("Table", 7999.99, 7));
        col.add(new Product("Knife", 599.99, 38));
        col.add(new Product("Sword", 3599.90, 2));
        col.add(new Product("Honey", 350, 164));
        col.add(new Product("Glass", 149.99, 1053));

        try (Writer fw = new FileWriter("src/main/resources/firstCourse/secondSemester/decorator/txtData.txt", true)) {
            for (Product p : col) {
                fw.write(p.toString() + "\n");
            }
            fw.flush();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }

    private static void myDeserialization() {
        try (Reader fr = new FileReader("src/main/resources/firstCourse/secondSemester/decorator/txtData.txt")) {
            Collection<Product> col = new ArrayList<>();
            for (int k = 0; k < Files.readAllLines(Paths.get("src/main/resources/firstCourse/secondSemester/decorator/txtData.txt")).size(); k++) {
                StringBuilder forName = new StringBuilder();
                for (int i = 0; i < 5; i++) {
                    forName.append((char) fr.read());
                }
                StringBuilder forNum = new StringBuilder();
                char c = (char) fr.read();
                while (c < '0' || c > '9') {
                    c = (char) fr.read();
                }
                while (c >= '0' && c <= '9') {
                    forNum.append(c);
                    c = (char) fr.read();
                }
                StringBuilder forPrice = new StringBuilder();
                while (c < '0' || c > '9') {
                    c = (char) fr.read();
                }
                while (c == '.' || (c >= '0' && c <= '9')) {
                    forPrice.append(c);
                    c = (char) fr.read();
                }
                while (c != 10) {
                    c = (char) fr.read();
                }
                col.add(new Product(forName.toString(), Double.parseDouble(forPrice.toString()), Integer.parseInt(forNum.toString())));
            }

            for (Product p : col) {
                System.out.println(p.toString());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
