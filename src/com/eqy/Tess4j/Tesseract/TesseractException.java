
package com.eqy.Tess4j.Tesseract;

@SuppressWarnings("serial")
public class TesseractException extends Exception {

    public TesseractException() {
        super();
    }

    public TesseractException(String message) {
        super(message);
    }

    public TesseractException(Throwable cause) {
        super(cause);
    }

    public TesseractException(String message, Throwable cause) {
        super(message, cause);
    }
}
