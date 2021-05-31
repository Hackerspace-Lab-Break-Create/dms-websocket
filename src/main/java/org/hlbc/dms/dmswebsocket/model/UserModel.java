package org.hlbc.dms.dmswebsocket.model;

import lombok.Data;

import java.util.Set;

@Data
public class UserModel {
  private String id;
  private boolean online;
  private Set<String> subscriptions;
}
