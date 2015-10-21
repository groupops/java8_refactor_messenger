package com.epam.service.reader;

import com.epam.model.Message;
import com.epam.model.MessageCache;

import java.util.Optional;

public class FromMemoryReader implements Reader {

  private final MessageCache messageCache;
  private String workingDirectory;

  public FromMemoryReader(MessageCache messageCache,
                          String workingDirectory) {
    if (messageCache == null) {
      messageCache = new MessageCache();
    }
    this.messageCache = messageCache;
    this.workingDirectory = workingDirectory;
  }

  @Override
  public Optional<Message> read(String path) {
    return messageCache.getMessageByPath(path);
  }

}
