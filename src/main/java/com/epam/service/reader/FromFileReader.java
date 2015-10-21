package com.epam.service.reader;

import com.epam.model.Message;
import com.epam.model.MessageCache;
import com.sun.istack.internal.logging.Logger;

import java.io.*;
import java.util.Optional;

public class FromFileReader implements Reader {

  private final Logger logger = Logger.getLogger(this.getClass());

  private String workingDirectory;
  private final MessageCache messageCache;

  public FromFileReader(MessageCache messageCache,
                        String workingDirectory) {
    this.messageCache = messageCache;
    this.workingDirectory = workingDirectory;
  }

  @Override
  public Optional<Message> read(String path) {
    Optional<Message> message = Optional.empty();
    if (messageCache.isMessageInPath(path)) {
      message = messageCache.getMessageByPath(path);
    } else {
      message = readMessageFromFile(path);
      addMessage(path, message);
    }
    return message;
  }

  private Optional<Message> readMessageFromFile(String path) {
    DataInputStream file_reader = null;
    Optional<Message> message = Optional.empty();
    try {
      file_reader = new DataInputStream(new FileInputStream(new File(path)));
      message.get().setContent(file_reader.readUTF());
    } catch (FileNotFoundException e) {
      logger.severe(String.format(
          "Error while opening message file for reading '%s'", path), e);
    } catch (IOException e) {
      logger.severe(String.format("Error while reading message file %s", path),
          e);
    } finally {
      close(path, file_reader);
    }
    return message;
  }

  private void addMessage(String path, Optional<Message> message) {
    if(message.isPresent()){
      messageCache.add(path, message.get());
    }
  }

  private void close(String path, DataInputStream file_reader) {
    if (file_reader != null) {
      try {
        file_reader.close();
      } catch (IOException e) {
        logger
            .warning(String.format("Error while closing message file %s", path),
                e);
      }
    }
  }

}
