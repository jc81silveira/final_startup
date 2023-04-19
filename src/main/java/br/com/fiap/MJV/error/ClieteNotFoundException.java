package br.com.fiap.MJV.error;

public class ClieteNotFoundException extends Exception{
    public ClieteNotFoundException() {
        super();
    }

    public ClieteNotFoundException(String message) {
        super(message);
    }

    public ClieteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClieteNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ClieteNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
