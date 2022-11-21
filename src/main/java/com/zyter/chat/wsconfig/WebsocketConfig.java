package com.zyter.chat.wsconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
@Configuration
@EnableScheduling
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer{

	@Value("${relay.host}")
	private String relayHost;
	
	@Value("${relay.port}")
	private int relayPort;

	@Value("${relay.login}")
	private String brokerLogin;
	
	@Value("${relay.passcode}")
	private String brokerPasscode;
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/zyter-websockets").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//registry.enableSimpleBroker("/topic");
		
		registry
        .enableStompBrokerRelay("/queue/", "/topic/")
        .setUserDestinationBroadcast("/topic/unresolved.user.dest")
        .setUserRegistryBroadcast("/topic/registry.broadcast")
        .setRelayHost(relayHost)
        .setRelayPort(relayPort)
        .setClientLogin(brokerLogin)
        .setClientPasscode(brokerPasscode);
		registry.setApplicationDestinationPrefixes("/app");
		
	}

	
}
