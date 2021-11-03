package io.project.kba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.project.kba.model.ProjectTask;
import io.project.kba.repository.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	private ProjectTaskRepository projectTaskRepository;

	public ProjectTask saveOrUpdateProjectTask(ProjectTask projectTask) {
		if (projectTask.getStatus() == null || projectTask.getStatus() == "") {
			projectTask.setStatus("To_Do");
		}

		return projectTaskRepository.save(projectTask);
	}

	public Iterable<ProjectTask> findAll() {
		return projectTaskRepository.findAll();
	}
	
	public ProjectTask findById(Long id) {
		return projectTaskRepository.findById(id).get();
	}
	
	public void delete(Long id) {
		ProjectTask projectTask = findById(id);
		projectTaskRepository.delete(projectTask);
	}
}
