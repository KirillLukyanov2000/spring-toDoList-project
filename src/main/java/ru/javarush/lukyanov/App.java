package ru.javarush.lukyanov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.javarush.lukyanov.entity.Task;
import ru.javarush.lukyanov.service.TaskService;

import java.util.List;


@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        TaskService taskService = context.getBean(TaskService.class);
        List<Task> all = taskService.findAll();
        all.forEach(task -> System.out.println(task));


    }
}
