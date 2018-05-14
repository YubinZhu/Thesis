package yubzhu.exception;

/**
 * Created by 朱宇斌 on 2018/5/11
 */

public class ApplicationException extends RuntimeException {
    private static final long serialVersionUID = 1922955215008755603L; //random

    public ApplicationException() {
        super();
    }

    public ApplicationException(String cause) {
        super(cause);
    }

    public ApplicationException(String msg, Throwable e) {
        super(msg, e);
    }
}
