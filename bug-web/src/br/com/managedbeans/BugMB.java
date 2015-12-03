package br.com.managedbeans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.entidades.Bug;
import br.com.entidades.Project;
import br.com.sessionbeans.BugRepository;
import br.com.sessionbeans.ProjectRepository;

@Named
@RequestScoped
public class BugMB {

	@Inject
	private BugRepository bugRepository;
	@Inject
	private ProjectRepository projectRepository;
	private Bug bug = new Bug();
	private Long projectId;
	private List<Bug> bugs;

	public void save() {
		Project project = this.projectRepository.findById(this.projectId);
		this.bug.setProject(project);

		if (this.getBug().getId() == null)
			this.bugRepository.add(this.getBug());
		else
			this.bugRepository.edit(this.getBug());

		this.bug = new Bug();
		this.bugs = null;
	}

	public void delete(Long id) {
		this.bugRepository.removeById(id);
		this.bugs = null;
	}

	public void prepareEdit(Long id) {
		this.bug = this.bugRepository.findById(id);
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Bug getBug() {
		return bug;
	}

	public List<Bug> getBugs() {
		if (this.bugs == null)
			this.bugs = this.bugRepository.findAll();
		return this.bugs;
	}

}
