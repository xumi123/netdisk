package com.sicnu.wzh.Config.WebSocket;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Component
public class WebSocketConfig {
    /**
     * 使用spring boot时，使用的是spring-boot的内置容器，
     * 如果要使用WebSocket，需要注入ServerEndpointExporter
     *
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
