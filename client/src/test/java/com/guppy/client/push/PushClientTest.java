package com.guppy.client.push;

import com.guppy.client.BaseTest;
import com.guppy.client.push.model.PushPayload;
import com.guppy.common.connection.HttpProxy;
import com.guppy.common.resp.APIConnectionException;
import com.guppy.common.resp.APIRequestException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by guppy
 * on 16-9-3 下午11:21.
 */
public class PushClientTest extends BaseTest {
    @Test(expected = IllegalArgumentException.class)
    public void test_invalid_json() {
        PushClient pushClient = new PushClient(MASTER_SECRET, APP_KEY);

        try {
            pushClient.sendPush("{aaa:'a}");
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_empty_string() {
        PushClient pushClient = new PushClient(MASTER_SECRET, APP_KEY);

        try {
            pushClient.sendPush("");
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_empty_password() {
        new HttpProxy("127.0.0.1", 8080, "", null);
    }

    @Test
    public void test_validate() {
        PushClient pushClient = new PushClient(MASTER_SECRET, APP_KEY);

        try {
            PushResult result = pushClient.sendPushValidate(PushPayload.alertAll("alert"));
            assertTrue("", result.isResultOK());
        } catch (APIRequestException e) {
            fail("Should not fail");
        } catch (APIConnectionException e) {
            e.printStackTrace();
        }
    }
}
