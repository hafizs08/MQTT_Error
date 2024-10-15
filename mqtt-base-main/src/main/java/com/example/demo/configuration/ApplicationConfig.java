package com.example.demo.configuration;

import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.client.MqttConnectionOptionsBuilder;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.configuration.properties.MqttProp;

@Configuration
public class ApplicationConfig {

  @Bean
  public MqttClient mqttClient(MqttProp prop) throws MqttException {
      Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);
      
      var options = new MqttConnectionOptionsBuilder()
          .automaticReconnect(true)
          .cleanStart(true)
          .connectionTimeout(30)
          .username(prop.getUsername())
          .password(prop.getPasswordBytes())
          .build();
  
      var client = new MqttClient(prop.getBrokerAddress(), prop.getClientId());
      
      try {
          logger.info("Connecting to MQTT broker at: " + prop.getBrokerAddress());
          client.connect(options);
          logger.info("Connected to MQTT broker");
      } catch (MqttException e) {
          logger.error("Failed to connect to MQTT broker", e);
          throw e;
      }
      
      return client;
  }

}
