package duke.testHelper;



import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class TestStream {

    private ByteArrayOutputStream outputStreamCaptor;
    private PrintStream printStream;

    protected PrintStream getPrintStream() {
        return this.printStream;
    }

    protected void setPrintStream(PrintStream ps) {
        this.printStream = ps;
    }

    protected void setOutputStreamCaptor(ByteArrayOutputStream baos) {
        this.outputStreamCaptor = baos;
    }

    protected ByteArrayOutputStream getOutputStreamCaptor() {
        return this.outputStreamCaptor;
    }

    // helper class
    protected String getOutput() {
        return this.outputStreamCaptor.toString();
    }

    @BeforeEach
    public void setOutputStream() {
        this.setOutputStreamCaptor(new ByteArrayOutputStream());
        this.setPrintStream(new PrintStream(this.getOutputStreamCaptor()));
    }

    @AfterEach
    public void unsetStreams() {
        this.setOutputStreamCaptor(null);
        this.setPrintStream(null);
    }
}
