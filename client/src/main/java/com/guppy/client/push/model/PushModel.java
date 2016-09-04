package com.guppy.client.push.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

/**
 * Created by guppy
 * on 16-9-3 下午11:26.
 */
public interface PushModel {

    public static Gson gson = new Gson();
    public JsonElement toJSON();
}
