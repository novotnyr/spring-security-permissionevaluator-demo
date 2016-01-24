package sk.upjs.ics.novotnyr.sspd;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private List<Project> projects = new LinkedList<Project>();

    public ProjectController() {
        projects.add(new Project("cassovia"));
        projects.add(new Project("fragopolis"));
        projects.add(new Project("leuchovia"));
    }

    @PostFilter("hasPermission(filterObject, 'view')")
    @RequestMapping
    public List<Project> getProjects() {
        return this.projects;
    }

    @RequestMapping("/{codeName}")
    @PreAuthorize("hasPermission(#codeName, 'project', 'view')")
    public Project getProject(@PathVariable String codeName) {
        for (Project project : this.projects) {
            if(project.getCodeName().equals(codeName)) {
                return project;
            }
        }
        throw new ProjectNotFoundException();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private class ProjectNotFoundException extends RuntimeException {
    }
}
