package com.guppy.common.connection;

import com.guppy.common.ServiceHelper;
import com.guppy.common.utils.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * Created by guppy
 * on 16-9-3 ÏÂÎç10:02.
 */
public class HttpProxy {
    private static final Logger LOG = LoggerFactory.getLogger(HttpProxy.class);
    private String host;
    private int port;
    private String username;
    private String password;
    private boolean authenticationNeeded;

    public HttpProxy(String host, int port) {
        this.authenticationNeeded = false;
        this.host = host;
        this.port = port;
    }

    public HttpProxy(String host, int port, String username, String password) {
        this(host, port);
        Preconditions.checkArgument(null != username, "username should not be null");
        Preconditions.checkArgument(null != password, "password should not be null");
        this.username = username;
        this.password = password;
        this.authenticationNeeded = true;
        LOG.info("Http Proxy - host:" + host + ", port:" + port + ", username:" + username + ", password:" + password);
    }

    public Proxy getNetProxy() {
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.host, this.port));
    }

    public boolean isAuthenticationNeeded() {
        return this.authenticationNeeded;
    }

    public String getProxyAuthorization() {
        return ServiceHelper.getBasicAuthorization(this.username, this.password);
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
