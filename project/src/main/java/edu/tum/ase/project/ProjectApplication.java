 package edu.tum.ase.project; 
 import org.hibernate.mapping.List;
import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.CommandLineRunner;
 import org.springframework.boot.SpringApplication;
 import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.tum.ase.project.model.Project;
import edu.tum.ase.project.service.ProjectService;

import java.util.ArrayList;

import javax.sql.DataSource;

 @SpringBootApplication
 public class ProjectApplication implements CommandLineRunner {

 private static final Logger log = LoggerFactory.getLogger(ProjectApplication.class); 

 
 @Autowired
private DataSource dataSource;
@Autowired
private ProjectService projectService;
public static void main(String[] args) { SpringApplication.run(ProjectApplication.class, args);
}
@Override
public void run(String... args) throws Exception {
log.info("DataSource = " + dataSource);
Project project = projectService.createProject(new Project("my-project")); log.info("ID of saved project = " + project.getId());
Project p = projectService.findByName("my-project");
log.info("ID of queried project = " + p.getId());
ArrayList<Project> projects = projectService.getProjects();
log.info("Length of project list = " + projects.size());
} }
