package com.guppy.common.resp;

import com.guppy.common.resp.ResponseWrapper.ErrorObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by guppy
 * on 16-9-3 ÏÂÎç10:18.
 */
public class APIRequestException extends Exception implements IRateLimiting {
    private static final long serialVersionUID = -3921022835186996212L;
    protected static Gson _gson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create();
    private final ResponseWrapper responseWrapper;

    public APIRequestException(ResponseWrapper responseWrapper) {
        super(responseWrapper.responseContent);
        this.responseWrapper = responseWrapper;
    }

    public int getStatus() {
        return this.responseWrapper.responseCode;
    }

    public long getMsgId() {
        ErrorObject eo = this.getErrorObject();
        return null != eo?eo.msg_id:0L;
    }

    public int getErrorCode() {
        ErrorObject eo = this.getErrorObject();
        return null != eo && null != eo.error?eo.error.code:-1;
    }

    public String getErrorMessage() {
        ErrorObject eo = this.getErrorObject();
        return null != eo && null != eo.error?eo.error.message:null;
    }

    public String toString() {
        return _gson.toJson(this);
    }

    private ErrorObject getErrorObject() {
        return this.responseWrapper.error;
    }

    public int getRateLimitQuota() {
        return this.responseWrapper.rateLimitQuota;
    }

    public int getRateLimitRemaining() {
        return this.responseWrapper.rateLimitRemaining;
    }

    public int getRateLimitReset() {
        return this.responseWrapper.rateLimitReset;
    }
}
