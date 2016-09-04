package com.guppy.client;

import com.guppy.client.device.DeviceClient;
import com.guppy.client.push.PushClient;
import com.guppy.client.report.ReportClient;
import com.guppy.client.schedule.ScheduleClient;

/**
 * Created by guppy
 * on 16-9-3 ÏÂÎç9:17.
 */
public class GPushClient {
    private final PushClient _pushClient;
    private final ReportClient _reportClient;
    private final DeviceClient _deviceClient;
    private final ScheduleClient _scheduleClient;


    public GPushClient(String masterSecret, String appKey) {
        _pushClient = new PushClient(masterSecret, appKey);
        _reportClient = new ReportClient(masterSecret, appKey);
        _deviceClient = new DeviceClient(masterSecret, appKey);
        _scheduleClient = new ScheduleClient(masterSecret, appKey);
    }
}
