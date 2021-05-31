package org.hlbc.dms.dmswebsocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DmsWebsocketApplication {

  public static void main(String[] args) {
    SpringApplication.run(DmsWebsocketApplication.class, args);
  }
}
