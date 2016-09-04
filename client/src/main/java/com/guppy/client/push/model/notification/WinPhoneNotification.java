package com.guppy.client.push.model.notification;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.Map;

/**
 * Created by guppy
 * on 16-9-3 下午11:45.
 */
public class WinPhoneNotification extends PlatformNotification {
    private static final String NOTIFICATION_WIN_PHONE = "winphone";

    private static final String TITLE = "title";
    private static final String _OPEN_PAGE = "_open_page";

    private final String title;
    private final String openPage;

    private WinPhoneNotification(Object alert, String title, String openPage,
                                 Map<String, String> extras,
                                 Map<String, Number> numberExtras,
                                 Map<String, Boolean> booleanExtras,
                                 Map<String, JsonObject> jsonExtras) {
        super(alert, extras, numberExtras, booleanExtras, jsonExtras);

        this.title = title;
        this.openPage = openPage;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static WinPhoneNotification alert(String alert) {
        return newBuilder().setAlert(alert).build();
    }


    @Override
    public String getPlatform() {
        return NOTIFICATION_WIN_PHONE;
    }

    @Override
    public JsonElement toJSON() {
        JsonObject json = super.toJSON().getAsJsonObject();

        if (null != title) {
            json.add(TITLE, new JsonPrimitive(title));
        }
        if (null != openPage) {
            json.add(_OPEN_PAGE, new JsonPrimitive(openPage));
        }

        return json;
    }


    public static class Builder extends PlatformNotification.Builder<WinPhoneNotification, Builder> {
        private String title;
        private String openPage;

        protected Builder getThis() {
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setOpenPage(String openPage) {
            this.openPage = openPage;
            return this;
        }

        public Builder setAlert(Object alert) {
            this.alert = alert;
            return this;
        }

        public WinPhoneNotification build() {
            return new WinPhoneNotification(alert, title, openPage,
                    extrasBuilder, numberExtrasBuilder, booleanExtrasBuilder, jsonExtrasBuilder);
        }
    }
}
