package com.guppy.common.resp;

/**
 * Created by guppy
 * on 16-9-3 ÏÂÎç10:17.
 */
public class APIConnectionException extends Exception {
    private static final long serialVersionUID = -2615370590441195647L;
    private boolean readTimeout = false;
    private int doneRetriedTimes = 0;

    public APIConnectionException(String message, Throwable e) {
        super(message, e);
    }

    public APIConnectionException(String message, Throwable e, int doneRetriedTimes) {
        super(message, e);
        this.doneRetriedTimes = doneRetriedTimes;
    }

    public APIConnectionException(String message, Throwable e, boolean readTimeout) {
        super(message, e);
        this.readTimeout = readTimeout;
    }

    public boolean isReadTimeout() {
        return this.readTimeout;
    }

    public int getDoneRetriedTimes() {
        return this.doneRetriedTimes;
    }
}
