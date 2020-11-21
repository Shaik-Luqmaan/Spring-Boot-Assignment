package com.Luqmaan.MiniJiraApplication.service;

import com.Luqmaan.MiniJiraApplication.entity.Task;

import java.util.List;

public interface TaskService {
    public List<Task> findAll();
    public Task findById(int theId);
    public List<Task> findByAssignee(String assignee);
    public void save(Task theTask);
    public void deleteById(int theId);
}
