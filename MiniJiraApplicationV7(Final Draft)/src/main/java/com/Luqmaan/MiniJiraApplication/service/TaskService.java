package com.Luqmaan.MiniJiraApplication.service;

import com.Luqmaan.MiniJiraApplication.entity.Task;

import java.util.List;

public interface TaskService {
     List<Task> findAll();
     Task findById(int theId);
     List<Task> findByAssignee(String assignee);
     void save(Task theTask);
     void deleteById(int theId);
     List<Task> searchBy(String theAssignee);
}
