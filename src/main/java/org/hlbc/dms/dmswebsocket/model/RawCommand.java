package org.hlbc.dms.dmswebsocket.model;

import lombok.Data;

@Data
public class RawCommand {

    private String to;
    private String from;
    private String message;

}
