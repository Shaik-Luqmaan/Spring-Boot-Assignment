package com.Luqmaan.MiniJiraApplication.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

}