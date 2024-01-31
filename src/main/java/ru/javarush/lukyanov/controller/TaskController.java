package ru.javarush.lukyanov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javarush.lukyanov.entity.Task;
import ru.javarush.lukyanov.service.TaskService;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

//    @GetMapping
//    public Page<Task> getAllTasksPageable(
//            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
//            @RequestParam(value = "limit", defaultValue = "10") @Min(1) @Max(100) Integer limit
//    ) {
//        return taskService.findAllTasksPageable(offset, limit);
//    }

    @GetMapping
    public String getAllTasks(Model model,
                              @RequestParam(value = "page", required = false, defaultValue = "1") @Min(0) Integer page,
                              @RequestParam(value = "limit", defaultValue = "10") @Min(1) @Max(100) Integer limit) {
        List<Task> tasks = taskService.findAllTasks((page - 1) * limit, limit);
        model.addAttribute("tasks", tasks);

        return "/tasks";
    }

    @PostMapping
    public void addNewTask(Model model, @RequestBody TaskInfo info) {
        Task task = taskService.createTask(info.getDescription(), info.getStatus());
    }

    @PostMapping("/{id}")
    public void editTask(Model model, @PathVariable(value = "id") Integer id, @RequestBody TaskInfo info) {
        if (isNull(id) || id <= 0) {
            throw new RuntimeException("Wrong Id");
        }
        Task task = taskService.updateTask(id, info.getDescription(), info.getStatus());
    }

    @DeleteMapping("/{id}")
    public String deleteTask(Model model, @PathVariable(value = "id") Integer id) {
        if (isNull(id) || id <= 0) {
            throw new RuntimeException("Wrong Id");
        }
        taskService.delete(id);
        return "/html/tasks";
    }
}
