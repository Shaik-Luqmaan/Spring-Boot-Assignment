package com.Luqmaan.MiniJiraApplication.controller;

import java.util.List;

import com.Luqmaan.MiniJiraApplication.entity.Task;
import com.Luqmaan.MiniJiraApplication.entity.TaskUpdate;
import com.Luqmaan.MiniJiraApplication.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/tasks")
@Slf4j
public class TaskController {

    private  final TaskService taskService;
    private static final String REDIRECT_TASKS_LIST = "redirect:/tasks/list";

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/list")
    public String listTasks(Model theModel, HttpServletRequest httpServletRequest){

        log.info("Listing tasks");

        List<Task> theTasks;
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

        log.info("Add form in admin");

        Task theTask = new Task();

        theTask.setStatus("To-Do");

        theModel.addAttribute("task", theTask);

        return "/tasks/add-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("id") int theId,
                                    Model model) {

          log.info("Form for Updating tasks");

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

            log.info("Saving tasks..");

            taskService.save(theTask);

            return REDIRECT_TASKS_LIST;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int theId) {

        log.info("Deleting tasks..");

        taskService.deleteById(theId);

        return REDIRECT_TASKS_LIST;
    }

    @PostMapping("/processFormAfterAdd")
    public String processFormAfterAdd(@Valid @ModelAttribute("task") Task task,
                                         BindingResult bindingResult)  {

        log.info("Processing from after add");

        if(bindingResult.hasErrors()) {
            return "/tasks/add-form";
        } else {
            taskService.save(task);
        }
        return REDIRECT_TASKS_LIST;
    }

    @PostMapping("/processFormAfterUpdate")
    public String processFormAfterUpdate(@RequestParam("id") int theId,
                                         @Valid @ModelAttribute("taskUpdate") TaskUpdate update,
                                         BindingResult bindingResult)  {

        log.info("Processing form after Update");

        if(bindingResult.hasErrors()) {

            return "/tasks/update-form";
        }else{
            Task task = taskService.findById(theId);
            task.setStatus(update.getStatus());
            taskService.save(task);
            return REDIRECT_TASKS_LIST;

        }
    }

    @GetMapping("/search")
    public String search(@RequestParam("assignee") String theAssignee,
                         Model theModel) {

        log.info("Searching tasks..");

        if (theAssignee.trim().isEmpty()) {
            return REDIRECT_TASKS_LIST;
        }
        else {

            List<Task> theTask =
                    taskService.searchBy(theAssignee);

            theModel.addAttribute("tasks", theTask);

            return "tasks/list-tasks";
        }

    }

    @GetMapping("/showSearch")
    public String showSearch(Model theModel) {

        log.info("Show Search Page");

        Task theTask = new Task();

        theModel.addAttribute("task", theTask);

        return "/tasks/search";
    }


}
