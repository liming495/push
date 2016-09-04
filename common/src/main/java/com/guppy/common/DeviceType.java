package com.guppy.common;

/**
 * Created by guppy
 * on 16-9-3 下午11:30.
 */
public enum  DeviceType {

    Android("android"),
    IOS("ios"),
    WinPhone("winphone");

    private final String value;

    private DeviceType(final String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
