package com.guppy.client.device;

import com.guppy.common.ClientConfig;
import com.guppy.common.ServiceHelper;
import com.guppy.common.connection.HttpProxy;
import com.guppy.common.connection.NativeHttpClient;

/**
 * Created by guppy
 * on 16-9-3 ÏÂÎç10:31.
 */
public class DeviceClient {

    private final NativeHttpClient _httpClient;
    private String hostName;
    private String devicesPath;
    private String tagsPath;
    private String aliasesPath;

    public DeviceClient(String masterSecret, String appKey) {
        this(masterSecret, appKey, null, ClientConfig.getInstance());
    }

    public DeviceClient(String masterSecret, String appKey, HttpProxy proxy, ClientConfig conf) {
        ServiceHelper.checkBasic(appKey, masterSecret);

        hostName = (String) conf.get(ClientConfig.DEVICE_HOST_NAME);
        devicesPath = (String) conf.get(ClientConfig.DEVICES_PATH);
        tagsPath = (String) conf.get(ClientConfig.TAGS_PATH);
        aliasesPath = (String) conf.get(ClientConfig.ALIASES_PATH);

        String authCode = ServiceHelper.getBasicAuthorization(appKey, masterSecret);
        _httpClient = new NativeHttpClient(authCode, proxy, conf);
    }
}
