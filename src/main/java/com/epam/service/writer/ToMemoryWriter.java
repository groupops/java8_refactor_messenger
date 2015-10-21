package com.epam.service.writer;

import com.epam.model.Message;
import com.epam.model.MessageCache;

public class ToMemoryWriter implements Writer {

  private final MessageCache messageCache;
  private final String workingDir;

  public ToMemoryWriter(MessageCache messageCache, String workingDir) {
    if(messageCache == null){
      messageCache = new MessageCache();
    }
    this.messageCache = messageCache;
    this.workingDir = workingDir;
  }

  @Override
  public void save(Message message) {
    messageCache.add(workingDir, message);
  }
}
