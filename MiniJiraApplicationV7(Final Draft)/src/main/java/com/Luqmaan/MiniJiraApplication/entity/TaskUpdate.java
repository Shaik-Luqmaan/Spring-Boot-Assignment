package com.Luqmaan.MiniJiraApplication.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskUpdate {

    private String status;

    private String assignee;

    private String taskName;

    private int id;
}
