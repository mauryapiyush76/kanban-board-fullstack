package io.project.kba.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.project.kba.model.ProjectTask;
import io.project.kba.service.ProjectTaskService;

@RestController
@RequestMapping("/api/v1/board")
@CrossOrigin
public class ProjectTaskController {

	@Autowired
	private ProjectTaskService projectTaskService;

	@PostMapping("")
	public ResponseEntity<?> addProjectTaskToBoard(@Valid @RequestBody ProjectTask projectTask,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();

			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
		}
		ProjectTask newProjectTask = projectTaskService.saveOrUpdateProjectTask(projectTask);

		return new ResponseEntity<ProjectTask>(newProjectTask, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public Iterable<ProjectTask> getAllProjectTasks() {
		return projectTaskService.findAll();
	}
	
	@GetMapping("/{pt_id}")
	public ResponseEntity<?> getProjectTaskById(@PathVariable Long pt_id){
		ProjectTask projectTask = projectTaskService.findById(pt_id);
		return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
	}
	
	@DeleteMapping("/{pt_id}")
	public ResponseEntity<?> deleteProjectTask(@PathVariable Long pt_id){
		projectTaskService.delete(pt_id);
		
		return new ResponseEntity<String>("Project Task Deleted", HttpStatus.OK);
	}
}
