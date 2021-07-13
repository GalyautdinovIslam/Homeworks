package firstCourse.secondSemester.decorator;

import java.io.IOException;
import java.io.OutputStream;

public class ProductOutputStream extends OutputStream {
    private final OutputStream os;

    public ProductOutputStream(OutputStream os) {
        this.os = os;
    }

    public void writeProduct(Product p) throws IOException {
        char[] name = p.getName().toCharArray();
        for (char c : name) {
            os.write(c >> 8);
            os.write(c);
        }
        long price = Double.doubleToLongBits(p.getPrice());
        for (int i = 56; i >= 0; i -= 8) {
            os.write((int) (price >> i));
        }
        int num = p.getNum();
        for (int i = 24; i >= 0; i -= 8) {
            os.write(num >> i);
        }
    }

    @Override
    public void write(int b) throws IOException {
        os.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        os.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        os.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        os.flush();
    }

    @Override
    public void close() throws IOException {
        os.close();
    }
}
