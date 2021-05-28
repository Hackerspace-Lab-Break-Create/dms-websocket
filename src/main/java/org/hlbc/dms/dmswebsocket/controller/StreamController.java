package org.hlbc.dms.dmswebsocket.controller;

import org.hlbc.dms.dmswebsocket.model.RawCommand;
import org.hlbc.dms.dmswebsocket.model.RawCommandResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class StreamController {

    @MessageMapping("/command")
    @SendTo("/pov/incomming")
    public RawCommandResponse handleRawCommand (RawCommand rawCommand){
        System.out.println("POV command:" + rawCommand);
        return new RawCommandResponse(rawCommand.getMessage());
    }
}
