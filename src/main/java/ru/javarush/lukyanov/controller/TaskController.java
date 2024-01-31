package ru.javarush.lukyanov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javarush.lukyanov.entity.Task;
import ru.javarush.lukyanov.service.TaskService;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public Page<Task> getAllTasksPageable(
            @RequestParam(value ="offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "10") @Min(1) @Max(100) Integer limit
    ) {
        return taskService.findAllTasksPageable(offset, limit);
    }

    @PostMapping
    public void addNewTask() {
        
    }
}
