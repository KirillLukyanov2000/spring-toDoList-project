package ru.javarush.lukyanov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.javarush.lukyanov.entity.Task;
import ru.javarush.lukyanov.enums.Status;
import ru.javarush.lukyanov.service.TaskService;

import java.util.List;


@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        TaskService taskService = context.getBean(TaskService.class);


        taskService.createTask("My Description",Status.IN_PROGRESS );

        List<Task> all = taskService.findAllTasks(0,30);
        all.forEach(task -> System.out.println(task));


        taskService.updateTask(24, "REV DESCR111", Status.DONE);

        List<Task> allRev = taskService.findAllTasks(0,30);
        allRev.forEach(task -> System.out.println(task));

        //taskService.delete(29);

        List<Task> allNew = taskService.findAllTasks(0,30);
        allNew.forEach(task -> System.out.println(task));

        System.out.println(taskService.getAllCount());

    }
}
