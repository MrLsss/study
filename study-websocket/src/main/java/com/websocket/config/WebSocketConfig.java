package com.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Copyright: Copyright (c) 2021
 *
 * <p> Description: WebSocket配置，开启WebSocket支持
 *
 * @author liushuai
 * @version 1.0.0
 * @createTime 2021年04月19日 22:15
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
