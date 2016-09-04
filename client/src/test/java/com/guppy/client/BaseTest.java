package com.guppy.client;

import org.junit.Before;

/**
 * Created by guppy
 * on 16-9-3 下午9:23.
 */
public class BaseTest {
    protected static final String APP_KEY ="dd1066407b044738b6479275";
    protected static final String MASTER_SECRET = "e8cc9a76d5b7a580859bcfa7";

    public static final String ALERT = "GPush Test - alert";
    public static final String MSG_CONTENT = "GPush Test - msgContent";

    public static final String REGISTRATION_ID1 = "0900e8d85ef";
    public static final String REGISTRATION_ID2 = "0a04ad7d8b4";

    protected GPushClient gPushClient = null;

    @Before
    public void before() {
        gPushClient = new GPushClient(MASTER_SECRET, APP_KEY);
    }
}
