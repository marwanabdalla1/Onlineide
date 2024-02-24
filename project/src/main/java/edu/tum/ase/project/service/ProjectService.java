package edu.tum.ase.project.service;
import edu.tum.ase.project.model.Project;
import edu.tum.ase.project.repository.ProjectRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
    public class ProjectService {
@Autowired
        private ProjectRepository projectRepository;


        public ProjectService(ProjectRepository projectRepository) {
            this.projectRepository = projectRepository;
        }
        public Project createProject(Project project) { 
            return projectRepository.save(project);
        }


        public Project findByName(String name) { 
            return projectRepository.findByName(name);
        }
        public Optional<Project> findbyId(String projectId) {
            return projectRepository.findById(projectId);
        }

        public boolean deleteProjectById(String projectid) {
            Optional<Project> projectOptional = projectRepository.findById(projectid);

            if (projectOptional.isPresent()) {
                projectRepository.deleteById(projectid);
                return true;
            }
            else {
                return false;
            }
        }

    public ResponseEntity<Project> updateProject(String projectId, Project updatedProject) {
        Optional<Project> existingProjectOptional = projectRepository.findById(projectId);

        if (existingProjectOptional.isPresent()) {
            Project existingProject = existingProjectOptional.get();

            // Update fields of the existing project with data from updatedProject
            existingProject.setName(updatedProject.getName());

            // Save the updated project using the JpaRepository's save method
            Project updatedProjectResult = projectRepository.save(existingProject);

            return new ResponseEntity<>(updatedProjectResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



        public ArrayList<Project> getProjects() { 
            return projectRepository.findAll();
        } 
}
