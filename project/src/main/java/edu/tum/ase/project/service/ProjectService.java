package edu.tum.ase.project.service;
import edu.tum.ase.project.model.Project;
import edu.tum.ase.project.repository.ProjectRepository; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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


        public ArrayList<Project> getProjects() { 
            return projectRepository.findAll();
        } 
}
