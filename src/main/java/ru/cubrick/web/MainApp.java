package ru.cubrick.web;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.cubrick.web.config.AppConfig;
import ru.cubrick.web.models.User;
import ru.cubrick.web.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.persist(new User("Jack", "Bauer"));

      List<User> users = userService.findAll();
      for (User user : users) {
         System.out.println(user);
      }

      userService.update(users.get(0), "Jonn", "Gordon");

      System.out.println(users.get(0));

      userService.remove(users.get(0));
      context.close();
   }
}
