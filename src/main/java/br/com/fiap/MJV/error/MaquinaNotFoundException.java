package br.com.fiap.MJV.error;

public class MaquinaNotFoundException extends Exception{
    public MaquinaNotFoundException() {
        super();
    }

    public MaquinaNotFoundException(String message) {
        super(message);
    }

    public MaquinaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MaquinaNotFoundException(Throwable cause) {
        super(cause);
    }

    protected MaquinaNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
