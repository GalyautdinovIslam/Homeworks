package firstCourse.secondSemester.myDataOutputStream;

import java.io.IOException;
import java.io.OutputStream;

public class DOS implements AutoCloseable {
    private final static int BITS_PER_BYTE = 8;
    private final static int START_FOR_CHAR = 8;
    private final static int START_FOR_INT = 24;

    private final OutputStream out;

    public DOS(OutputStream out) {
        this.out = out;
    }

    public void writeInt(int n) throws IOException {
        for (int i = START_FOR_INT; i >= 0; i -= BITS_PER_BYTE) {
            out.write(n >> i);
        }
    }

    public void writeChar(char c) throws IOException {
        for (int i = START_FOR_CHAR; i >= 0; i -= BITS_PER_BYTE) {
            out.write(c >> i);
        }
    }

    public void flush() throws IOException {
        out.flush();
    }

    @Override
    public void close() throws Exception {
        out.close();
    }
}
