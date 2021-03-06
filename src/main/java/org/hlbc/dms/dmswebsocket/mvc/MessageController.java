/*
 * Copyright 2014-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.hlbc.dms.dmswebsocket.mvc;

import org.hlbc.dms.dmswebsocket.data.ActiveWebSocketUserRepository;
import org.hlbc.dms.dmswebsocket.data.InstantMessage;
import org.hlbc.dms.dmswebsocket.data.User;
import org.hlbc.dms.dmswebsocket.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Controller for managing {@link Message} instances.
 *
 * @author Rob Winch
 */
@Controller
public class MessageController {

  private final SimpMessageSendingOperations messagingTemplate;

  private final ActiveWebSocketUserRepository activeUserRepository;

  @Autowired
  public MessageController(
      ActiveWebSocketUserRepository activeUserRepository,
      SimpMessageSendingOperations messagingTemplate) {
    this.activeUserRepository = activeUserRepository;
    this.messagingTemplate = messagingTemplate;
  }

  @MessageMapping("/im")
  public void im(InstantMessage im, @CurrentUser User currentUser) {
    im.setFrom(currentUser.getEmail());
    this.messagingTemplate.convertAndSendToUser(im.getTo(), "/queue/messages", im);
    this.messagingTemplate.convertAndSendToUser(im.getFrom(), "/queue/messages", im);
  }

  @SubscribeMapping("/users")
  public List<String> subscribeMessages() {
    return this.activeUserRepository.findAllActiveUsers();
  }
}
