package com.guppy.common.resp;

import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * Created by guppy
 * on 16-9-3 ÏÂÎç10:17.
 */
public class ResponseWrapper implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(ResponseWrapper.class);
    private static final int RESPONSE_CODE_NONE = -1;
    private static final long serialVersionUID = -4227962073448507865L;
    private static Gson _gson = new Gson();
    private static JsonParser jsonParser = new JsonParser();
    public int responseCode = -1;
    public String responseContent;
    public ResponseWrapper.ErrorObject error;
    public int rateLimitQuota;
    public int rateLimitRemaining;
    public int rateLimitReset;

    public ResponseWrapper() {
    }

    public void setRateLimit(String quota, String remaining, String reset) {
        if(null != quota) {
            try {
                this.rateLimitQuota = Integer.parseInt(quota);
                this.rateLimitRemaining = Integer.parseInt(remaining);
                this.rateLimitReset = Integer.parseInt(reset);
                LOG.debug("JPush API Rate Limiting params - quota:" + quota + ", remaining:" + remaining + ", reset:" + reset);
            } catch (NumberFormatException var5) {
                LOG.debug("Unexpected - parse rate limiting headers error.");
            }

        }
    }

    public void setErrorObject() {
        this.error = new ResponseWrapper.ErrorObject();
        this.error.error = new ResponseWrapper.ErrorEntity();

        try {
            JsonElement e = jsonParser.parse(this.responseContent);
            JsonObject errorObj = null;
            if(e instanceof JsonArray) {
                JsonArray errorMsg = (JsonArray)e;

                for(int i = 0; i < errorMsg.size(); ++i) {
                    if(errorMsg.get(i).getAsJsonObject().has("error")) {
                        errorObj = errorMsg.get(i).getAsJsonObject();
                        break;
                    }
                }
            } else if(e instanceof JsonObject) {
                errorObj = (JsonObject)e;
            }

            if(null != errorObj) {
                JsonObject var7 = errorObj;
                if(errorObj.has("msg_id")) {
                    this.error.msg_id = errorObj.get("msg_id").getAsLong();
                }

                if(errorObj.has("error")) {
                    var7 = (JsonObject)errorObj.get("error");
                }

                if(var7.has("code")) {
                    this.error.error.code = var7.get("code").getAsInt();
                }

                if(var7.has("message")) {
                    this.error.error.message = var7.get("message").getAsString();
                }
            }
        } catch (JsonSyntaxException var5) {
            LOG.error("Unexpected - responseContent:" + this.responseContent, var5);
        } catch (Exception var6) {
            LOG.error("Unexpected - responseContent:" + this.responseContent, var6);
        }

    }

    public boolean isServerResponse() {
        return this.responseCode / 100 == 2?true:this.responseCode > 0 && null != this.error && this.error.error.code > 0;
    }

    public String toString() {
        return _gson.toJson(this);
    }

    public static class ErrorEntity {
        public int code;
        public String message;

        public ErrorEntity() {
        }

        public String toString() {
            return ResponseWrapper._gson.toJson(this);
        }
    }

    public static class ErrorObject {
        public long msg_id;
        public ResponseWrapper.ErrorEntity error;

        public ErrorObject() {
        }
    }
}
