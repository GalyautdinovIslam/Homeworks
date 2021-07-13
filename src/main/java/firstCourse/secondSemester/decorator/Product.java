package firstCourse.secondSemester.decorator;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {
    private static final String FIRST_SPLIT = ": ";
    private static final String SECOND_SPLIT = " pieces, ";
    private static final String THIRD_SPLIT = " cu each.";

    private String name;
    private double price;
    private int num;

    public Product(String name, double price, int num) {
        this.name = name;
        this.price = price;
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Double.compare(product.getPrice(), getPrice()) == 0 &&
                getNum() == product.getNum() &&
                getName().equals(product.getName());
    }

    @Override
    public String toString() {
        return getName() + FIRST_SPLIT + getNum() + SECOND_SPLIT + getPrice() + THIRD_SPLIT;
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(getName(), getPrice(), getNum());
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getNum() {
        return num;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
