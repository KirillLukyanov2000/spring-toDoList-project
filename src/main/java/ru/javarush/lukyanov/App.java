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

        Task newTask = new Task();
        newTask.setDescription("My Description");
        newTask.setStatus(Status.IN_PROGRESS);
        taskService.createTask(newTask);
        newTask.setDescription("REV DESCR");
        newTask.setStatus(Status.DONE);

        taskService.updateTask(newTask);

        List<Task> all = taskService.findAllTasks();
        all.forEach(task -> System.out.println(task));

        taskService.delete(19L);

        List<Task> allNew = taskService.findAllTasks();
        allNew.forEach(task -> System.out.println(task));

    }
}
