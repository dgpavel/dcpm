package ro.dma.dcpm.shared;

public class MyRuntimeException extends RuntimeException {
    public MyRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
