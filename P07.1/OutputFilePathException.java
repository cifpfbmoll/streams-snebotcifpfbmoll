import java.util.*;

public class OutputFilePathException extends Throwable {
    private String error = "";

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return this.error;
    }

    public OutputFilePathException() {}

    public OutputFilePathException(String error) {
        this.error = error;
    }
}
