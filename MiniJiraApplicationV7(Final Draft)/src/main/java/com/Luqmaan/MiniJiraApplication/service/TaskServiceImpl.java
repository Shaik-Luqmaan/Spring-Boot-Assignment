package com.Luqmaan.MiniJiraApplication.service;

import com.Luqmaan.MiniJiraApplication.dao.TaskRepository;
import com.Luqmaan.MiniJiraApplication.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository theTaskRepository){
        taskRepository = theTaskRepository;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAllByOrderByAssignee();
    }


    @Override
    public Task findById(int theId) {
        Optional<Task> result = taskRepository.findById(theId);
        Task theTask = null;
        if (result.isPresent()) {
            theTask = result.get();
        }
        return theTask;
    }

    @Override
    public List<Task> findByAssignee(String assignee) {
        return taskRepository.findByAssigneeOrderById(assignee);
    }

    @Override
    public void save(Task theTask) {
        taskRepository.save(theTask);
    }

    @Override
    public void deleteById(int theId) {
        taskRepository.deleteById(theId);
    }

    @Override
    public List<Task> searchBy(String theAssignee){
        return taskRepository.findByAssigneeContainsAllIgnoreCase(theAssignee);
    }
}
