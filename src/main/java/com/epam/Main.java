package com.epam;

import com.epam.model.Message;
import com.epam.model.UserCommand;
import com.epam.service.MessagingServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class Main {

  private static BufferedReader reader;
  private static final String workingDir =
      "/home/magdy/Desktop/messages_folder";

  public static void main(String[] args) {
    runApplication();
  }

  public static void runApplication() {
    reader = new BufferedReader(new InputStreamReader(System.in));
    String input;
    do {
      MessagingServiceImpl messaging_service =
          new MessagingServiceImpl(workingDir);
      System.out.print(
          "Please save one of these options: \n" +
              "[w]rite message\n" +
              "[r]ead message\n" +
              "[q]uit\n" +
              "Please save your choice and press <Enter>: "
      );

      try {
        input = reader.readLine();
        actionForInput(input, messaging_service,
            reader);
      } catch (IOException e) {
        throw new RuntimeException(
            "The message cannot be read.", e);
      }
    } while (!input.toLowerCase().equals(UserCommand.QUIT));
  }

  private static void actionForInput(String input, MessagingServiceImpl messaging_service,
                                     BufferedReader reader)
      throws IOException {
    if (input.toLowerCase().equals(UserCommand.WRITE)) {
      makeWriteAction(messaging_service, reader);
    } else if (input.toLowerCase().equals(UserCommand.READ)) {
      makeReadAction(messaging_service, reader);
    }
  }

  private static void makeReadAction(MessagingServiceImpl messaging_service,
                                     BufferedReader reader)
      throws IOException {
    System.out.print("Please enter the message path: ");
    String path = reader.readLine();
    Optional<Message> message = messaging_service.readMessage(path);
    if (message.isPresent()) {
      System.out.println(message.get().getContent());
    }
  }

  private static void makeWriteAction(MessagingServiceImpl messaging_service,
                                      BufferedReader reader)
      throws IOException {
    System.out.print("Please enter your message: ");
    String message = reader.readLine();
    messaging_service.saveMessage(message);
  }

}