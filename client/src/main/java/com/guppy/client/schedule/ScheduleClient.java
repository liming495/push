package com.guppy.client.schedule;

import com.guppy.common.ClientConfig;
import com.guppy.common.ServiceHelper;
import com.guppy.common.connection.HttpProxy;
import com.guppy.common.connection.NativeHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by guppy
 * on 16-9-3 ÏÂÎç10:31.
 */
public class ScheduleClient {

    private static final Logger LOG = LoggerFactory.getLogger(ScheduleClient.class);
    private final NativeHttpClient _httpClient;

    private String hostName;
    private String schedulePath;

    // If not present, true by default.
    private int apnsProduction;

    // If not present, the default value is 86400(s) (one day)
    private long timeToLive;

    public ScheduleClient(String masterSecret, String appkey) {
        this(masterSecret, appkey, null, ClientConfig.getInstance());
    }

    public ScheduleClient(String masterSecret, String appKey, HttpProxy proxy, ClientConfig conf) {
        ServiceHelper.checkBasic(appKey, masterSecret);
        hostName = (String) conf.get(ClientConfig.SCHEDULE_HOST_NAME);
        schedulePath = (String) conf.get(ClientConfig.SCHEDULE_PATH);
        apnsProduction = (Integer) conf.get(ClientConfig.APNS_PRODUCTION);
        timeToLive = (Long) conf.get(ClientConfig.TIME_TO_LIVE);

        String authCode = ServiceHelper.getBasicAuthorization(appKey, masterSecret);
        this._httpClient = new NativeHttpClient(authCode, proxy, conf);
    }
}
