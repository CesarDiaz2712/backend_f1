package com.cesardiaz.backend.f1.backendf1.core.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.ajp.AbstractAjpProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {


	@Value("${ajp.port}")
    int ajpPort;
 
    @Value("${ajp.enabled}")
    boolean ajpEnabled;
    
	@Value("${server.port}")
    int httpPort;


	@Bean
	public ServletWebServerFactory servletContainer() throws UnknownHostException {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		if(ajpEnabled) {
			tomcat.addAdditionalTomcatConnectors(redirectConnector());
		}
		return tomcat;
	}
	
   
    private Connector redirectConnector() throws UnknownHostException {
       Connector ajpConnector = new Connector("AJP/1.3");
       AbstractAjpProtocol<?> protocol=(AbstractAjpProtocol<?>) ajpConnector.getProtocolHandler();
       protocol.setAddress(InetAddress.getByName("0.0.0.0"));
       protocol.setSecret("4jpS3cr3t");
       ajpConnector.setPort(ajpPort);
       ajpConnector.setRedirectPort(httpPort);
       ajpConnector.setURIEncoding("UTF-8");
       return ajpConnector;
    }

}
