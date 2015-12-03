package br.com.managedbeans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.entidades.Project;
import br.com.sessionbeans.ProjectRepository;

@Named
@RequestScoped
public class ProjectMB {

	@Inject
	private ProjectRepository projectRepository;
	private Project project = new Project();
	private List<Project> projects;

	public void save() {
		if (this.getProject().getId() == null)
			this.projectRepository.add(project);
		else
			this.projectRepository.edit(project);
		this.project = new Project();
		this.projects = null;
	}

	public void delete(Long id) {
		this.projectRepository.removeById(id);
		this.projects = null;
	}

	public void prepareEdit(Long id) {
		this.project = this.projectRepository.findById(id);
	}

	public List<Project> getProjects() {
		if (this.projects == null)
			this.projects = this.projectRepository.findAll();
		return this.projects;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
