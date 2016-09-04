package com.guppy.client.push;

import com.google.gson.annotations.Expose;
import com.guppy.common.resp.BaseResult;

/**
 * Created by guppy
 * on 16-9-3 下午11:50.
 */
public class PushResult extends BaseResult {
    private static final long serialVersionUID = 93783137655776743L;

    @Expose
    public long msg_id;
    @Expose public int sendno;
}
