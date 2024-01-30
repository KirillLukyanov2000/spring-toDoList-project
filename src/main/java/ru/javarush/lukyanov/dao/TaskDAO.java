package ru.javarush.lukyanov.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javarush.lukyanov.entity.Task;

@Repository
@Transactional(readOnly = true)
public interface TaskDAO extends JpaRepository <Task, Long> {
}
