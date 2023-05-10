package com.kbstar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@EnableWebSocketMessageBroker
@Configuration
public class StomWebSocketConfig implements WebSocketMessageBrokerConfigurer{

    @Value("${serviceserver}")
    String serviceServer;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        127.0.0.1로 접속했던 애들도 이 웹소켓에 접속할 수 있게끔 하겠다는 뜻
//        ws, wss, chbot의 3개의 웹소켓을 만들었다
        registry.addEndpoint("/ws").setAllowedOrigins("http://127.0.0.1",serviceServer).withSockJS();
        registry.addEndpoint("/wss").setAllowedOrigins("http://127.0.0.1",serviceServer).withSockJS();
        registry.addEndpoint("/chbot").setAllowedOrigins("http://127.0.0.1").withSockJS();

    }

    /* 어플리케이션 내부에서 사용할 path를 지정할 수 있음 */
//    나가는 통로를 만들어 주는것. ex /send, /sendadm
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/send","/sendadm");
    }
}