package com.guppy.client.report;

import com.guppy.common.ClientConfig;
import com.guppy.common.ServiceHelper;
import com.guppy.common.connection.HttpProxy;
import com.guppy.common.connection.NativeHttpClient;

/**
 * Created by guppy
 * on 16-9-3 ÏÂÎç10:31.
 */
public class ReportClient {

    private final NativeHttpClient _httpClient;
    private String _hostName;
    private String _receivePath;
    private String _userPath;
    private String _messagePath;

    public ReportClient(String masterSecret, String appKey) {
        this(masterSecret, appKey, null, ClientConfig.getInstance());
    }

    public ReportClient(String masterSecret, String appKey, HttpProxy proxy, ClientConfig conf) {
        ServiceHelper.checkBasic(appKey, masterSecret);

        _hostName = (String) conf.get(ClientConfig.REPORT_HOST_NAME);
        _receivePath = (String) conf.get(ClientConfig.REPORT_RECEIVE_PATH);
        _userPath = (String) conf.get(ClientConfig.REPORT_USER_PATH);
        _messagePath = (String) conf.get(ClientConfig.REPORT_MESSAGE_PATH);

        String authCode = ServiceHelper.getBasicAuthorization(appKey, masterSecret);
        _httpClient = new NativeHttpClient(authCode, proxy, conf);
    }
}
