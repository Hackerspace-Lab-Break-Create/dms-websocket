package org.hlbc.dms.dmswebsocket.model;

import lombok.Data;

@Data
public class Message {
  private String from;
  private String to;
  private String text;
}
