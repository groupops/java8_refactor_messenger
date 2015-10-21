package com.epam.service;

import com.epam.model.Message;

import java.util.Optional;

public interface MessagingService {

  void saveMessage(String message);

  Optional<Message> readMessage(String path);

}