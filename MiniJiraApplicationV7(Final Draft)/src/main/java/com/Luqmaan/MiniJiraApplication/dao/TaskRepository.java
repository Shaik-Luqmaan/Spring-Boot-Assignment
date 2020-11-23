package com.Luqmaan.MiniJiraApplication.dao;

import com.Luqmaan.MiniJiraApplication.entity.Task;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository  extends JpaRepository<Task, Integer> {
    List<Task> findByAssigneeOrderById(String assignee);
     List<Task> findAllByOrderByAssignee();

     //Search
    public List<Task> findByAssigneeContainsAllIgnoreCase(String theAssignee);
}