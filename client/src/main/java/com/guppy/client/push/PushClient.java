package com.guppy.client.push;

import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.guppy.client.push.model.PushPayload;
import com.guppy.common.ClientConfig;
import com.guppy.common.ServiceHelper;
import com.guppy.common.connection.HttpProxy;
import com.guppy.common.connection.NativeHttpClient;
import com.guppy.common.resp.APIConnectionException;
import com.guppy.common.resp.APIRequestException;
import com.guppy.common.resp.BaseResult;
import com.guppy.common.resp.ResponseWrapper;
import com.guppy.common.utils.Preconditions;
import com.guppy.common.utils.StringUtils;

/**
 * Created by guppy
 * on 16-9-3 下午9:29.
 */
public class PushClient {
    private final NativeHttpClient _httpClient;
    private String _baseUrl;
    private String _pushPath;
    private String _pushValidatePath;
    private JsonParser _jsonParser;
    private int _apnsProduction;
    private long _timeToLive;

    /**
     * Create a Push Client.
     *
     * @param masterSecret API access secret of the appKey.
     * @param appKey The KEY of one application on GPush.
     */
    public PushClient(String masterSecret, String appKey) {
        this(masterSecret, appKey, null, ClientConfig.getInstance());
    }

    public PushClient(String masterSecret, String appKey, HttpProxy proxy, ClientConfig conf) {
        this._jsonParser = new JsonParser();
        ServiceHelper.checkBasic(appKey, masterSecret);
        this._baseUrl = (String)conf.get("push.host.name");
        this._pushPath = (String)conf.get("push.path");
        this._pushValidatePath = (String)conf.get("push.validate.path");
        this._apnsProduction = ((Integer)conf.get("apns.production")).intValue();
        this._timeToLive = ((Long)conf.get("time.to.live")).longValue();
        String authCode = ServiceHelper.getBasicAuthorization(appKey, masterSecret);
        this._httpClient = new NativeHttpClient(authCode, proxy, conf);
    }

    public PushResult sendPush(PushPayload pushPayload) throws APIConnectionException, APIRequestException {
        Preconditions.checkArgument(! (null == pushPayload), "pushPayload should not be null");

        if (_apnsProduction > 0) {
            pushPayload.resetOptionsApnsProduction(true);
        } else if(_apnsProduction == 0) {
            pushPayload.resetOptionsApnsProduction(false);
        }

        if (_timeToLive >= 0) {
            pushPayload.resetOptionsTimeToLive(_timeToLive);
        }

        ResponseWrapper response = _httpClient.sendPost(_baseUrl + _pushPath, pushPayload.toString());

        return BaseResult.fromResponse(response, PushResult.class);
    }

    public PushResult sendPushValidate(PushPayload pushPayload) throws APIConnectionException, APIRequestException {
        Preconditions.checkArgument(! (null == pushPayload), "pushPayload should not be null");

        if (_apnsProduction > 0) {
            pushPayload.resetOptionsApnsProduction(true);
        } else if(_apnsProduction == 0) {
            pushPayload.resetOptionsApnsProduction(false);
        }

        if (_timeToLive >= 0) {
            pushPayload.resetOptionsTimeToLive(_timeToLive);
        }

        ResponseWrapper response = _httpClient.sendPost(_baseUrl + _pushValidatePath, pushPayload.toString());

        return BaseResult.fromResponse(response, PushResult.class);
    }

    public PushResult sendPush(String payloadString) throws APIConnectionException, APIRequestException {
        Preconditions.checkArgument(StringUtils.isNotEmpty(payloadString), "pushPayload should not be empty");

        try {
            _jsonParser.parse(payloadString);
        } catch (JsonParseException e) {
            Preconditions.checkArgument(false, "payloadString should be a valid JSON string.");
        }

        ResponseWrapper response = _httpClient.sendPost(_baseUrl + _pushPath, payloadString);

        return BaseResult.fromResponse(response, PushResult.class);
    }
}
