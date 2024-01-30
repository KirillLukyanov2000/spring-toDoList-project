package ru.javarush.lukyanov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javarush.lukyanov.dao.TaskDAO;
import ru.javarush.lukyanov.entity.Task;

import java.util.List;
@Service
@Transactional(readOnly = true)
public class TaskService {

    private final TaskDAO taskDAO;
    @Autowired
    public TaskService(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    public List<Task> findAll(){
        List<Task> all = taskDAO.findAll();
        return all;
    }
}
