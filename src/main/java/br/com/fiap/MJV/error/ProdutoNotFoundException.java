package br.com.fiap.MJV.error;

public class ProdutoNotFoundException extends Exception{
    public ProdutoNotFoundException() {
        super();
    }

    public ProdutoNotFoundException(String message) {
        super(message);
    }

    public ProdutoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProdutoNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ProdutoNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
