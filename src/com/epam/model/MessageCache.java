package com.epam.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MessageCache {

  private final Map<String, Message> cachedMessages = new HashMap<>();

  public boolean isMessageInPath(String path) {
    return cachedMessages.containsKey(path);
  }

  public void add(String path, Message message) {
      cachedMessages.put(path, message);
  }

  public Optional<Message> getMessageByPath(String path) {
    return Optional.ofNullable(cachedMessages.get(path));
  }

}
