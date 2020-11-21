package com.Luqmaan.MiniJiraApplication.controller;
import java.util.List;
import com.Luqmaan.MiniJiraApplication.entity.Task;
import com.Luqmaan.MiniJiraApplication.entity.TaskUpdate;
import com.Luqmaan.MiniJiraApplication.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Binding;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/list")
    public String listTasks(Model theModel, HttpServletRequest httpServletRequest){

        List<Task> theTasks = null;
        String assignee = httpServletRequest.getUserPrincipal().getName();
        boolean isAdmin = httpServletRequest.isUserInRole("ADMIN");
        if(isAdmin) {
            theTasks = taskService.findAll();
        } else {
            theTasks = taskService.findByAssignee(assignee);
        }
        theModel.addAttribute("tasks", theTasks);
        return "/tasks/list-tasks";

    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        Task theTask = new Task();
        if(theTask == null){
            return "error-page";
        }

        theTask.setStatus("To-Do");
        theModel.addAttribute("task", theTask);

        return "/tasks/add-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("id") int theId,
                                    Model model) {
           Task theTask = taskService.findById(theId);

            model.addAttribute("task",theTask);
            model.addAttribute("task",theTask);

            if(theTask == null){
            return "error-page";
            }

            TaskUpdate update = new TaskUpdate();
            update.setStatus(theTask.getStatus());
            update.setId(theTask.getId());
            model.addAttribute("taskUpdate",update);

        return "/tasks/update-form";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute("task") Task theTask) {

            taskService.save(theTask);

            return "redirect:/tasks/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int theId) {

            taskService.deleteById(theId);

        return "redirect:/tasks/list";
    }

    @PostMapping("/processFormAfterAdd")
    public String processFormAfterAdd(@Valid @ModelAttribute("task") Task task,
                                         BindingResult bindingResult)  {

        if(bindingResult.hasErrors()) {
            return "/tasks/add-form";
        } else {
            taskService.save(task);
        }
        return "redirect:/tasks/list";
    }

    @PostMapping("/processFormAfterUpdate")
    public String processFormAfterUpdate(@RequestParam("id") int theId,
                                         @Valid @ModelAttribute("taskUpdate") TaskUpdate update,
                                         BindingResult bindingResult)  {

        if(bindingResult.hasErrors()) {

            return "/tasks/update-form";
        }else{
            Task task = taskService.findById(theId);
            task.setStatus(update.getStatus());
            taskService.save(task);
            return "redirect:/tasks/list";

        }
    }


}
