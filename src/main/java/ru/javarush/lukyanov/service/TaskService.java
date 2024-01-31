package ru.javarush.lukyanov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javarush.lukyanov.dao.TaskDAO;
import ru.javarush.lukyanov.entity.Task;

import java.awt.print.Pageable;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TaskService {

    private final TaskDAO taskDAO;

    @Autowired
    public TaskService(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    public List<Task> findAllTasks() {
        List<Task> all = taskDAO.findAll();
        return all;
    }
    public Page<Task> findAllTasksPageable(Integer offset, Integer limit) {
        Page<Task> pageTasks = taskDAO.findAll(PageRequest.of(offset, limit));
        return pageTasks;
    }

    @Transactional
    public Task createTask(Task task) {
        taskDAO.save(task);
        return task;
    }

    @Transactional
    public Task updateTask(Task task) {
        taskDAO.updateTask(task.getDescription(), task.getStatus(), task.getId());
        return task;
    }

    @Transactional
    public void delete(Long id) {
        taskDAO.deleteById(id);
    }
}
