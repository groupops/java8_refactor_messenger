package com.epam.service;

import com.epam.model.FileInfo;
import com.epam.model.Message;
import com.epam.model.MessageCache;
import com.epam.service.reader.Reader;
import com.epam.service.reader.FromMemoryReader;
import com.epam.service.writer.Writer;
import com.epam.service.writer.ToMemoryWriter;
import com.sun.istack.internal.logging.Logger;

import java.util.Optional;

/**
 * Created by magdy on 23.09.15.
 */
public class MessagingServiceImpl implements MessagingService {

  private Logger logger = Logger.getLogger(this.getClass());

  private String workingDirectory;
  private int idCounter = 1;
  private MessageCache messageCache;
  private Writer memoryWriter;
  private Reader memoryReader;

  public MessagingServiceImpl(String workingDirectory) {
    this.memoryWriter = new ToMemoryWriter(messageCache, workingDirectory);
    this.memoryReader = new FromMemoryReader(messageCache, workingDirectory);
  }

  @Override
  public Optional<Message> readMessage(String path) {
    return memoryReader.read(path);
  }

  @Override
  public void saveMessage(String messageContent) {
    Message message = new Message(idCounter, messageContent);
    memoryWriter.save(message);
    idCounter++;
  }

  public FileInfo getMessageFileInfoById(int id) {
    return new FileInfo(workingDirectory, id);
  }

}
