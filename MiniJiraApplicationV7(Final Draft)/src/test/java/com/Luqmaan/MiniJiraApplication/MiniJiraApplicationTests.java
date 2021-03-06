package com.Luqmaan.MiniJiraApplication;

import com.Luqmaan.MiniJiraApplication.dao.TaskRepository;
import com.Luqmaan.MiniJiraApplication.entity.Task;
import com.Luqmaan.MiniJiraApplication.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MiniJiraApplicationTests {

	@MockBean
	private TaskRepository taskRepository;

	@Autowired
	private TaskService taskService;

	List<Task> list = new ArrayList<Task>();

	@Test
	public void findAll(){
		Task firstTask = new
				Task(1,"Luqmaan","Spring Boot","luqmaan@zemosolabs.com","To-Do");

		Task secondTask = new
				Task(2,"Riaz","Spring Core","riaz@zemosolabs.com","To-Do");

		list.add(firstTask);
		list.add(secondTask);

		//Mock
		Mockito.when(taskRepository.findAll()).thenReturn(list);

		//Test
		List<Task> taskList = taskService.findAll();

		assertEquals(2,taskList.size());

	}

	@Test
	public void findByIdTest(){

		Task task=new Task(3,"Mateen","Java","mateen@zemosolabs.com","Done");

		Mockito.when(taskRepository.findById(task.getId())).thenReturn(Optional.empty());

//		assertEquals(task,taskService.findById(task.getId()));
		Task task2 = taskService.findById(task.getId());
		System.out.println(task2.getId());
	}

	@Test
	public void save(){

		Task task=new Task(4,"Aziz","Java","aziz@zemosolabs.com","Done");

		taskService.save(task);

		Mockito.verify(taskRepository,Mockito.times(1)).save(task);

	}

	@Test
	public void deleteById(){

		Task task=new Task(5,"Rayyan","CSS","rayyan@zemosolabs.com","Done");

		taskService.deleteById(task.getId());

		Mockito.verify(taskRepository, Mockito.times(1)).deleteById(task.getId());

	}

}
