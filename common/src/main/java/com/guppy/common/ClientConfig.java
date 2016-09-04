package com.guppy.common;

import java.util.HashMap;

/**
 * Created by guppy
 * on 16-9-3 ����9:46.
 */
public class ClientConfig extends HashMap<String, Object> {
    public static final String DEVICE_HOST_NAME = "device.host.name";
    public static final Object DEVICE_HOST_NAME_SCHEMA = String.class;
    public static final String DEVICES_PATH = "devices.path";
    public static final Object DEVICES_PATH_SCHEMA = String.class;
    public static final String TAGS_PATH = "tags.path";
    public static final Object TAGS_PATH_SCHEMA = String.class;
    public static final String ALIASES_PATH = "aliases.path";
    public static final Object ALIASES_PATH_SCHEMA = String.class;
    public static final String PUSH_HOST_NAME = "push.host.name";
    public static final Object PUSH_HOST_NAME_SCHEMA = String.class;
    public static final String PUSH_PATH = "push.path";
    public static final Object PUSH_PATH_SCHEMA = String.class;
    public static final String PUSH_VALIDATE_PATH = "push.validate.path";
    public static final Object PUSH_VALIDATE_PATH_SCHMEA = String.class;
    public static final String REPORT_HOST_NAME = "report.host.name";
    public static final Object REPORT_HOST_NAME_SCHEMA = String.class;
    public static final String REPORT_RECEIVE_PATH = "report.receive.path";
    public static final Object REPORT_RECEIVE_PATH_SCHEMA = String.class;
    public static final String REPORT_USER_PATH = "report.user.path";
    public static final Object REPORT_USER_PATH_SCHEMA = String.class;
    public static final String REPORT_MESSAGE_PATH = "report.message.path";
    public static final Object REPORT_MESSAGE_PATH_SCHEMA = String.class;
    public static final String SCHEDULE_HOST_NAME = "schedule.host.name";
    public static final Object SCHEDULE_HOST_NAME_SCHEMA = String.class;
    public static final String SCHEDULE_PATH = "schedule.path";
    public static final Object SCHEDULE_PATH_SCHEMA = String.class;
    public static final String SSL_VERSION = "ssl.version";
    public static final Object SSL_VERSION_SCHEMA = String.class;
    public static final String DEFAULT_SSL_VERSION = "TLS";
    public static final String MAX_RETRY_TIMES = "max.retry.times";
    public static final Object MAX_RETRY_TIMES_SCHEMA = Integer.class;
    public static final int DEFULT_MAX_RETRY_TIMES = 3;
    public static final String READ_TIMEOUT = "read.timeout";
    public static final Object READ_TIMEOUT_SCHEMA = Integer.class;
    public static final int DEFAULT_READ_TIMEOUT = 30000;
    public static final String CONNECTION_TIMEOUT = "connection.timeout";
    public static final Object CONNECTION_TIMEOUT_SCHEMA = Integer.class;
    public static final int DEFAULT_CONNECTION_TIMEOUT = 5000;
    public static final String APNS_PRODUCTION = "apns.production";
    public static final Object APNS_PRODUCTION_SCHEMA = Integer.class;
    public static final int DEFAULT_APNS_PRODUCTION = -1;
    public static final String TIME_TO_LIVE = "time.to.live";
    public static final Object TIME_TO_LIVE_SCHEMA = Long.class;
    public static final long DEFAULT_TIME_TO_LIVE = -1L;
    private static ClientConfig instance = new ClientConfig();

    private ClientConfig() {
        super(32);
        //this.put("device.host.name", "https://device.jpush.cn");
        this.put("devices.path", "/v3/devices");
        this.put("tags.path", "/v3/tags");
        this.put("aliases.path", "/v3/aliases");
        this.put("push.host.name", "http://localhost:8080/server");
        this.put("push.path", "/v3/push");
        this.put("push.validate.path", "/v3/push/validate");
        //this.put("report.host.name", "https://report.jpush.cn");
        this.put("report.receive.path", "/v3/received");
        this.put("report.user.path", "/v3/users");
        this.put("report.message.path", "/v3/messages");
        //this.put("schedule.host.name", "https://api.jpush.cn");
        this.put("schedule.path", "/v3/schedules");
        this.put("ssl.version", "TLS");
        this.put("max.retry.times", Integer.valueOf(3));
        this.put("read.timeout", Integer.valueOf(30000));
        this.put("connection.timeout", Integer.valueOf(5000));
        this.put("apns.production", Integer.valueOf(-1));
        this.put("time.to.live", Long.valueOf(-1L));
    }

    public static ClientConfig getInstance() {
        return instance;
    }

    public void setDeviceHostName(String hostName) {
        this.put("device.host.name", hostName);
    }

    public void setPushHostName(String hostName) {
        this.put("push.host.name", hostName);
    }

    public void setReportHostName(String hostName) {
        this.put("report.host.name", hostName);
    }

    public void setScheduleHostName(String hostName) {
        this.put("schedule.host.name", hostName);
    }

    public void setSSLVersion(String sslVer) {
        this.put("ssl.version", sslVer);
    }

    public void setMaxRetryTimes(int maxRetryTimes) {
        this.put("max.retry.times", Integer.valueOf(maxRetryTimes));
    }

    public void setReadTimeout(int readTimeout) {
        this.put("read.timeout", Integer.valueOf(readTimeout));
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.put("connection.timeout", Integer.valueOf(connectionTimeout));
    }

    public String getSSLVersion() {
        return (String)this.get("ssl.version");
    }

    public Integer getMaxRetryTimes() {
        return (Integer)this.get("max.retry.times");
    }

    public Integer getReadTimeout() {
        return (Integer)this.get("read.timeout");
    }

    public Integer getConnectionTimeout() {
        return (Integer)this.get("connection.timeout");
    }

    public void setApnsProduction(boolean production) {
        if(production) {
            this.put("apns.production", Integer.valueOf(1));
        } else {
            this.put("apns.production", Integer.valueOf(0));
        }

    }

    public void setTimeToLive(long timeToLive) {
        this.put("time.to.live", Long.valueOf(timeToLive));
    }

    public void setGlobalPushSetting(boolean apnsProduction, long timeToLive) {
        this.setApnsProduction(apnsProduction);
        this.setTimeToLive(timeToLive);
    }
}
