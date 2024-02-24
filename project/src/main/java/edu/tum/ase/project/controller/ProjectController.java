package edu.tum.ase.project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.tum.ase.project.model.Project;
import edu.tum.ase.project.service.ProjectService;;




@RestController
@RequestMapping("/projects")

public class ProjectController {
    @Autowired
    private ProjectService projectService;



    @PostMapping("/createproject")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project createdProject = projectService.createProject(project);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/getproject/{projectId}", method = RequestMethod.GET)
    public Optional<Project> getProject(@PathVariable String projectId) {
        return projectService.findbyId(projectId);
    }


    @PutMapping (path = "/updateproject/{projectid}")
    public ResponseEntity<Project> updateProject (@PathVariable String projectid, @RequestBody Project updatedProject) {

        return projectService.updateProject(projectid, updatedProject);

    }


    @DeleteMapping("/deleteProject/{projectID}")
    public ResponseEntity<String> deleteProject(@PathVariable String projectID) {
        boolean deleted = projectService.deleteProjectById(projectID);

        if (deleted) {
            return new ResponseEntity<>("Project deleted successfuly", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Project not found or could not be delted", HttpStatus.NOT_FOUND);
        }
    }







    
}
