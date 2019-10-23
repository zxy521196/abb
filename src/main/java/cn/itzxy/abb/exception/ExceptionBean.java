package cn.itzxy.abb.exception;

public class ExceptionBean extends RuntimeException{
    private String message;

    public ExceptionBean(String message) {
        super(message);
    }
}
