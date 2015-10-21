package com.epam.service.reader;

import com.epam.model.Message;

import java.util.Optional;

public interface Reader {

  Optional<Message> read(String path);

}
