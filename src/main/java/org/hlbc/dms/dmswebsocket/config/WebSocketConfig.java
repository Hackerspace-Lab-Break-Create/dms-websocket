package org.hlbc.dms.dmswebsocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.Session;
import org.springframework.session.web.socket.config.annotation.AbstractSessionWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableScheduling
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractSessionWebSocketMessageBrokerConfigurer<Session> {

  public void configureMessageBroker(MessageBrokerRegistry config) {
    config.enableSimpleBroker("/queue/", "/topic/");
    //config.enableSimpleBroker("/secured/user/queue/specific-user");
    config.setApplicationDestinationPrefixes("/app");
    //config.setUserDestinationPrefix("/secured/user");
  }

  @Override
  protected void configureStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
    /*stompEndpointRegistry
        .addEndpoint("/secured/room")
        .withSockJS();

     */
    stompEndpointRegistry.addEndpoint("/messages").withSockJS();
  }
}
