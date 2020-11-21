package com.Luqmaan.MiniJiraApplication.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="tasks")
public class Task {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "assignee_name")
    @NotNull
    @Size(min=1,message = "Assignee name is required")
    private String assignee;

    @NotNull
    @Size(min=1,message = "Task is required")
    @Column(name="task_name")
        private String taskName;

    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@zemosolabs\\.com$", message="Please enter a valid email")
    @Column(name="email")
    private String email;

    @NotNull
    @Size(min=1,message = "Status is required")
    @Column(name="status")
    private String status;

    public Task(){

    }

    public Task(int id, String assignee, String taskName,  String email, String status) {
        this.id = id;
        this.assignee = assignee;
        this.taskName = taskName;
        this.email = email;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}