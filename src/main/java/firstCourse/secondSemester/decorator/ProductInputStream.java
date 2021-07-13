package firstCourse.secondSemester.decorator;

import java.io.IOException;
import java.io.InputStream;

public class ProductInputStream extends InputStream {
    private final InputStream is;

    public ProductInputStream(InputStream is) {
        this.is = is;
    }

    public Product readProduct() throws IOException {
        StringBuilder forName = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            char c = (char) (is.read() << 8 | is.read());
            forName.append(c);
        }
        long forPrice = 0;
        for (int i = 0; i < 8; i++) {
            forPrice = forPrice << 8;
            forPrice |= is.read();
        }
        int forNum = 0;
        for (int i = 0; i < 4; i++) {
            forNum = forNum << 8;
            forNum |= is.read();
        }
        return new Product(forName.toString(), Double.longBitsToDouble(forPrice), forNum);
    }

    @Override
    public int read() throws IOException {
        return is.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return is.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return is.read(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return is.skip(n);
    }

    @Override
    public int available() throws IOException {
        return is.available();
    }

    @Override
    public void close() throws IOException {
        is.close();
    }

    @Override
    public void mark(int readlimit) {
        is.mark(readlimit);
    }

    @Override
    public void reset() throws IOException {
        is.reset();
    }

    @Override
    public boolean markSupported() {
        return is.markSupported();
    }
}
