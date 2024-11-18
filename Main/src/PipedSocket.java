import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * PipedSocket simulates a network socket using piped streams and helper class.
 * @Author: Sameer Dadoo
 *
 * @Version 11/17/2024
 */
public class PipedSocket extends Socket {
    private final InputStream inputStream;
    private final OutputStream outputStream;

    public PipedSocket(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    @Override
    public InputStream getInputStream() {
        return inputStream;
    }

    @Override
    public OutputStream getOutputStream() {
        return outputStream;
    }
}
