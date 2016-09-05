package com.jsu.chat.init;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
import java.util.Set;

/**
 * com.jsu.chat.init
 *
 * @desc
 * @author:EumJi
 * @year: 2016
 * @month: 09
 * @day: 02
 * @time: 2016/9/2
 */

public class SocketConfig implements ServerApplicationConfig {
    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> set) {
        return null;
    }

    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> set) {
        return set;
    }
}
