package org.hlbc.dms.dmswebsocket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class PeriodicBroadcastService {

  @Autowired private SimpMessagingTemplate simpMessagingTemplate;

  // @Scheduled(fixedRate = 5000)
  public void sendBroadcast() {

    simpMessagingTemplate.convertAndSend("/pov/incomming", "yo");
  }
}
