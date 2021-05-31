package org.hlbc.dms.dmswebsocket.controller;

import org.hlbc.dms.dmswebsocket.model.Message;
import org.hlbc.dms.dmswebsocket.model.OutputMessage;
import org.hlbc.dms.dmswebsocket.model.RawCommand;
import org.hlbc.dms.dmswebsocket.model.RawCommandResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class StreamController {

  @Autowired private SimpUserRegistry userRegistry;

  @Autowired private SimpMessagingTemplate simpMessagingTemplate;

  @MessageMapping("/command")
  @SendTo("/pov/incomming")
  public RawCommandResponse handleRawCommand(RawCommand rawCommand) {
    // System.out.println("prefixes: "+ );

    System.out.println("User count" + userRegistry.getUserCount());
    System.out.println("POV command:" + rawCommand);
    return new RawCommandResponse(rawCommand.getMessage());
  }

  @MessageMapping("/secured/room")
  public void sendSpecific(
      @Payload Message msg, Principal user, @Header("simpSessionId") String sessionId)
      throws Exception {
    OutputMessage out =
        new OutputMessage(
            msg.getFrom(), msg.getText(), new SimpleDateFormat("HH:mm").format(new Date()));
    simpMessagingTemplate.convertAndSendToUser(
        msg.getTo(), "/secured/user/queue/specific-user", out);
  }
}
