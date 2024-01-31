package ru.javarush.lukyanov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javarush.lukyanov.dao.TaskDAO;
import ru.javarush.lukyanov.entity.Task;
import ru.javarush.lukyanov.enums.Status;


import java.util.List;

import static java.util.Objects.isNull;

@Service
@Transactional(readOnly = true)
public class TaskService {

    private final TaskDAO taskDAO;

    @Autowired
    public TaskService(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    public List<Task> findAllTasks(Integer offset, Integer limit) {

        return taskDAO.findAll();
    }

    public Page<Task> findAllTasksPageable(Integer offset, Integer limit) {
        Page<Task> pageTasks = taskDAO.findAll(PageRequest.of(offset, limit));
        return pageTasks;
    }

    public long getAllCount() {
        return taskDAO.count();
    }

    public Task getTaskById(Integer id) {
        return taskDAO.findById(id).orElseThrow();
    }

    @Transactional
    public Task createTask(String description, Status status) {
        Task task = new Task();
        task.setDescription(description);
        task.setStatus(status);
        taskDAO.save(task);
        return task;
    }

    @Transactional
    public Task updateTask(Integer id, String description, Status status) {
        Task taskUpd = taskDAO.findById(id).orElseThrow();
        taskUpd.setDescription(description);
        taskUpd.setStatus(status);
        taskDAO.updateTask(taskUpd.getDescription(), taskUpd.getStatus(), taskUpd.getId());
        return taskUpd;
    }

    @Transactional
    public void delete(Integer id) {

        taskDAO.deleteById(id);
    }
}
