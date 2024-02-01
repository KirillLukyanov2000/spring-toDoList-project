package ru.javarush.lukyanov.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javarush.lukyanov.entity.Task;
import ru.javarush.lukyanov.service.TaskService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAllTasks(Model model,
                              @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                              @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        List<Task> tasks = taskService.findAllTasks((page - 1) * limit, limit);
        model.addAttribute("tasks", tasks);
        model.addAttribute("current_page", page);
        int totalPages = (int) Math.ceil(1.0 * taskService.getAllCount() / limit);
        if (totalPages > 1) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("page_numbers", pageNumbers);
        }
        return "tasks";
    }


    @PostMapping
    public String addNewTask(Model model, @RequestBody TaskInfo info) {
        Task task = taskService.createTask(info.getDescription(), info.getStatus());
        return getAllTasks(model, 1, 10);
    }

    @PostMapping("/{id}")
    public String editTask(Model model, @PathVariable(value = "id") Integer id, @RequestBody TaskInfo info) {
        if (isNull(id) || id <= 0) {
            throw new RuntimeException("Wrong Id");
        }
        Task task = taskService.updateTask(id, info.getDescription(), info.getStatus());
        return getAllTasks(model, 1, 10);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(Model model, @PathVariable(value = "id") Integer id) {
        if (isNull(id) || id <= 0) {
            throw new RuntimeException("Wrong Id");
        }
        taskService.delete(id);
        return getAllTasks(model, 1, 10);
    }
}
