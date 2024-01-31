package ru.javarush.lukyanov.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javarush.lukyanov.entity.Task;
import ru.javarush.lukyanov.enums.Status;

@Repository
@Transactional(readOnly = true)
public interface TaskDAO extends JpaRepository<Task, Integer> {
    @Transactional
    @Modifying
    @Query("update Task t set t.description = ?1, t.status = ?2 where t.id = ?3")
    int updateTask(String description, Status status, Integer id);

}
