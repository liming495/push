package com.guppy.client.push.model.audience;

/**
 * Created by guppy
 * on 16-9-3 下午11:39.
 */
public enum AudienceType {

    TAG("tag"),
    TAG_AND("tag_and"),
    ALIAS("alias"),
    SEGMENT("segment"),
    REGISTRATION_ID("registration_id");

    private final String value;
    private AudienceType(final String value) {
        this.value = value;
    }
    public String value() {
        return this.value;
    }
}
